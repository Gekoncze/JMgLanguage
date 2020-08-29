package cz.mg.language.tasks.mg.resolver.resolvers.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.MgResolveFunctionCommandsTask;
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
    protected FunctionContext getContext() {
        return (FunctionContext) super.getContext();
    }

    @Override
    public MgFunction getOutput() {
        return function;
    }

    @Override
    protected MgFunction onResolveComponent() {
        function = new MgFunction(logicalFunction.getName());
        getContext().setFunction(function);

        function.setOperator(new MgOperator(
            logicalFunction.getOperator().getName()
        ));
        function.getOperator().setType(MgOperator.Type.valueOf(logicalFunction.getOperator().getType().name()));
        function.getOperator().setPriority(logicalFunction.getOperator().getPriority());

        createAndPostponeMore(
            MgResolveVariableDefinitionTask.class,
            logicalFunction.getInput(),
            variables -> function.getInput().addCollectionLast(variables)
        );

        createAndPostponeMore(
            MgResolveVariableDefinitionTask.class,
            logicalFunction.getOutput(),
            variables -> function.getOutput().addCollectionLast(variables)
        );

        postpone(
            new MgResolveFunctionCommandsTask(
                getContext(),
                logicalFunction,
                function
            )
        );

        return function;
    }
}
