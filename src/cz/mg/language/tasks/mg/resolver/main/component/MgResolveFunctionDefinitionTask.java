package cz.mg.language.tasks.mg.resolver.main.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.MgOperator;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.command.MgResolveCommandTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.context.FunctionContext;


public class MgResolveFunctionDefinitionTask extends MgResolveComponentDefinitionTask {
    @Input
    private final MgLogicalFunction logicalFunction;

    @Output
    private MgFunction function;

    public MgResolveFunctionDefinitionTask(Context context, MgLogicalFunction logicalFunction) {
        super(new FunctionContext(context), logicalFunction);
        this.logicalFunction = logicalFunction;
    }

    @Override
    protected FunctionContext getContext() {
        return (FunctionContext) super.getContext();
    }

    public MgFunction getFunction() {
        return function;
    }

    @Override
    protected void onResolveComponent() {
        function = new MgFunction(logicalFunction.getName());
        getContext().setFunction(function);

        function.setOperator(new MgOperator(
            logicalFunction.getOperator().getName()
        ));
        function.getOperator().setType(MgOperator.Type.valueOf(logicalFunction.getOperator().getType().name()));
        function.getOperator().setPriority(logicalFunction.getOperator().getPriority());

        for(MgLogicalVariable logicalInput : logicalFunction.getInput()){
            postpone(MgResolveLocalVariableDefinitionTask.class, () -> {
                MgResolveLocalVariableDefinitionTask task = new MgResolveLocalVariableDefinitionTask(getContext(), logicalInput);
                task.run();
                function.getInput().addLast(task.getVariable());
                function.updateVariableOffsetCache();
            });
        }

        for(MgLogicalVariable logicalOutput : logicalFunction.getOutput()){
            postpone(MgResolveLocalVariableDefinitionTask.class, () -> {
                MgResolveLocalVariableDefinitionTask task = new MgResolveLocalVariableDefinitionTask(getContext(), logicalOutput);
                task.run();
                function.getOutput().addLast(task.getVariable());
                function.updateVariableOffsetCache();
            });
        }

        for(MgLogicalCommand logicalCommand : logicalFunction.getCommands()){
            postpone(MgResolveCommandTask.class, () -> {
                MgResolveCommandTask task = MgResolveCommandTask.create(new CommandContext(getContext()), logicalCommand);
                task.run();
                function.getCommands().addLast(task.getCommand());
                function.updateVariableOffsetCache();
            });
        }
    }
}
