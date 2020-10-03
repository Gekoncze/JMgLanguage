package cz.mg.language.tasks.mg.resolver.main.component;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.types.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;
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
        if(logicalFunction.getOperator() == null){
            function = new MgFunction(logicalFunction.getName());
        } else {
            function = new MgOperator(logicalFunction.getName(), new MgOperatorInfo(
                logicalFunction.getOperator().getName(),
                MgOperatorInfo.Type.valueOf(logicalFunction.getOperator().getType().name()),
                logicalFunction.getOperator().getPriority()
            ));
        }
        getContext().setFunction(function);

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
