package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolvers.contexts.FunctionContext;


public class MgResolveFunctionTask extends MgResolveTask<MgFunction> {
    @Input
    private final MgLogicalFunction logicalFunction;

    @Output
    private MgFunction function;

    public MgResolveFunctionTask(Store<MgFunction> store, Context context, MgLogicalFunction logicalFunction) {
        super(store, new FunctionContext(context));
        this.logicalFunction = logicalFunction;
    }

    @Override
    protected MgFunction onResolve() {
        function = new MgFunction(logicalFunction.getName());
        ((FunctionContext)getContext()).setFunction(function);

        createAndPostponeMore(
            MgResolveComponentStampTask.class,
            function.getStamps(),
            stamps -> function.setStamps(stamps)
        );

        createAndPostponeMore(
            MgResolveVariableTask.class,
            function.getInput(),
            variables -> function.setInput(variables)
        );

        createAndPostponeMore(
            MgResolveVariableTask.class,
            function.getOutput(),
            variables -> function.setOutput(variables)
        );

        createAndPostponeMore(
            MgResolveVariableTask.class,
            function.getLocal(),
            variables -> function.setLocal(variables)
        );

        function.setInstructions(resolveInstructions()); // TODO
        function.setOperator(resolveOperator()); // TODO

        return function;
    }
}
