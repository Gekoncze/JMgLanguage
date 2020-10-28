package cz.mg.language.tasks.mg.resolver.component;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.*;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.entities.mg.runtime.components.types.functions.*;
import cz.mg.language.tasks.mg.resolver.command.MgResolveCommandTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.component.structured.FunctionContext;


public class MgResolveClassFunctionDefinitionTask extends MgResolveComponentDefinitionTask {
    @Input
    private final MgLogicalFunction logicalFunction;

    @Output
    private MgFunction function;

    public MgResolveClassFunctionDefinitionTask(Context context, MgLogicalFunction logicalFunction) {
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
    protected void onResolveComponent(List<MgStamp> stamps) {
        if(logicalFunction instanceof MgLogicalOperator){
            if(logicalFunction instanceof MgLogicalBinaryOperator){
                function = new MgBinaryOperator(logicalFunction.getName(), ((MgLogicalOperator) logicalFunction).getPriority());
            } else if(logicalFunction instanceof MgLogicalLunaryOperator){
                function = new MgLunaryOperator(logicalFunction.getName(), ((MgLogicalOperator) logicalFunction).getPriority());
            } else if(logicalFunction instanceof MgLogicalRunaryOperator){
                function = new MgRunaryOperator(logicalFunction.getName(), ((MgLogicalOperator) logicalFunction).getPriority());
            } else {
                throw new RuntimeException();
            }
        } else {
            switch (getType(stamps, Type.GLOBAL)){
                case GLOBAL:
                    function = new MgGlobalFunction(logicalFunction.getName());
                    break;
                case TYPE:
                    function = new MgTypeFunction(logicalFunction.getName());
                    break;
                case INSTANCE:
                    function = new MgInstanceFunction(logicalFunction.getName());
                    break;
                default:
                    throw new RuntimeException();
            }
            function.getStamps().addCollectionLast(stamps);
        }
        getContext().setFunction(function);

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
