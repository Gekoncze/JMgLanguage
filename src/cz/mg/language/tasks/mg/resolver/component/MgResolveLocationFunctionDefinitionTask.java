package cz.mg.language.tasks.mg.resolver.component;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.components.MgLogicalFunction;
import cz.mg.language.entities.mg.logical.components.MgLogicalVariable;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalCommand;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgGlobalFunction;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.MgOperatorInfo;
import cz.mg.language.tasks.mg.resolver.command.MgResolveCommandTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.FunctionContext;


public class MgResolveLocationFunctionDefinitionTask extends MgResolveComponentDefinitionTask {
    @Input
    private final MgLogicalFunction logicalFunction;

    @Output
    private MgFunction function;

    public MgResolveLocationFunctionDefinitionTask(Context context, MgLogicalFunction logicalFunction) {
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
        if(logicalFunction.getOperator() == null){
            function = new MgGlobalFunction(logicalFunction.getName());
            function.getStamps().addCollectionLast(globalStampOnly(stamps));
        } else {
            function = new MgOperator(logicalFunction.getName(), new MgOperatorInfo(
                logicalFunction.getOperator().getName(),
                MgOperatorInfo.Type.valueOf(logicalFunction.getOperator().getType().name()),
                logicalFunction.getOperator().getPriority()
            ));
            function.getStamps().addCollectionLast(globalStampOnly(stamps));
        }
        getContext().setFunction(function);

        for(MgLogicalVariable logicalInput : logicalFunction.getInput()){
            postpone(MgResolveFunctionVariableDefinitionTask.class, () -> {
                MgResolveFunctionVariableDefinitionTask task = new MgResolveFunctionVariableDefinitionTask(getContext(), logicalInput);
                task.run();
                function.getInput().addLast(task.getVariable());
                function.updateVariableOffsetCache();
            });
        }

        for(MgLogicalVariable logicalOutput : logicalFunction.getOutput()){
            postpone(MgResolveFunctionVariableDefinitionTask.class, () -> {
                MgResolveFunctionVariableDefinitionTask task = new MgResolveFunctionVariableDefinitionTask(getContext(), logicalOutput);
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
