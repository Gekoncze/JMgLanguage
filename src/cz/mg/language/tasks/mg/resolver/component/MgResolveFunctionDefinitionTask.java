package cz.mg.language.tasks.mg.resolver.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.tasks.mg.resolver.command.MgResolveCommandTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.component.structured.FunctionContext;


public abstract class MgResolveFunctionDefinitionTask extends MgResolveComponentDefinitionTask {
    @Input
    protected final MgLogicalFunction logicalFunction;

    @Output
    protected MgFunction function;

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

    protected void onResolveComponentChildren(){
        for(MgLogicalVariable logicalInput : logicalFunction.getInput()){
            postpone(MgResolveFunctionVariableDefinitionTask.class, () -> {
                MgResolveFunctionVariableDefinitionTask task = new MgResolveFunctionVariableDefinitionTask(getContext(), function, logicalInput);
                task.run();
                function.getInputVariables().addLast(task.getVariable());
            });
        }

        for(MgLogicalVariable logicalOutput : logicalFunction.getOutput()){
            postpone(MgResolveFunctionVariableDefinitionTask.class, () -> {
                MgResolveFunctionVariableDefinitionTask task = new MgResolveFunctionVariableDefinitionTask(getContext(), function, logicalOutput);
                task.run();
                function.getOutputVariables().addLast(task.getVariable());
            });
        }

        for(MgLogicalCommand logicalCommand : logicalFunction.getCommands()){
            postpone(MgResolveCommandTask.class, () -> {
                MgResolveCommandTask task = MgResolveCommandTask.create(new CommandContext(getContext()), logicalCommand);
                task.run();
                function.getCommands().addLast(task.getCommand());
            });
        }
    }
}
