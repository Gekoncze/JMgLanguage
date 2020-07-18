package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.MgResolveInstructionsTask;
import cz.mg.language.tasks.mg.resolver.Store;
import cz.mg.language.tasks.mg.resolver.contexts.FunctionContext;


public class MgResolveFunctionDefinitionTask extends MgResolveComponentDefinitionTask<MgFunction> {
    @Input
    private final MgLogicalFunction logicalFunction;

    @Output
    private MgFunction function;

    public MgResolveFunctionDefinitionTask(Store<MgFunction> store, Context context, MgLogicalFunction logicalFunction) {
        super(store, new FunctionContext(context), logicalFunction);
        this.logicalFunction = logicalFunction;
    }

    @Override
    public MgFunction getOutput() {
        return function;
    }

    @Override
    protected MgFunction onResolveComponent() {
        function = new MgFunction(logicalFunction.getName());
        function.setOperator(logicalFunction.getOperator());
        ((FunctionContext)getContext()).setFunction(function);

        createAndPostponeMore(
            MgResolveVariableDefinitionTask.class,
            function.getInput(),
            variables -> function.setInput(variables)
        );

        createAndPostponeMore(
            MgResolveVariableDefinitionTask.class,
            function.getOutput(),
            variables -> function.setOutput(variables)
        );

        createAndPostponeMore(
            MgResolveVariableDefinitionTask.class,
            function.getLocal(),
            variables -> function.setLocal(variables)
        );

        postpone(
            new MgResolveInstructionsTask(
                getContext(),
                logicalFunction,
                function
            )
        );

        return function;
    }
}
