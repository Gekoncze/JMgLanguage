package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.entities.mg.logical.parts.commands.*;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public abstract class MgResolveCommandTask extends MgResolverTask {
    public static MgResolveCommandTask create(CommandContext context, MgLogicalCommand logicalCommand){
        if(logicalCommand instanceof MgLogicalExpressionCommand){
            return new MgResolveExpressionCommandTask(context, (MgLogicalExpressionCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalIfCommand){
            return new MgResolveIfCommandTask(context, (MgLogicalIfCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalWhileCommand){
            return new MgResolveWhileCommandTask(context, (MgLogicalWhileCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalContinueCommand) {
            return new MgResolveContinueCommandTask(context, (MgLogicalContinueCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalBreakCommand){
            return new MgResolveBreakCommandTask(context, (MgLogicalBreakCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalReturnCommand){
            return new MgResolveReturnCommandTask(context, (MgLogicalReturnCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalRollbackCommand){
            return new MgResolveRollbackCommandTask(context, (MgLogicalRollbackCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalSwitchCommand){
            return new MgResolveSwitchCommandTask(context, (MgLogicalSwitchCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalElseCommand){
            return new MgResolveElseCommandTask(context, (MgLogicalElseCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalCaseCommand){
            return new MgResolveCaseCommandTask(context, (MgLogicalCaseCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalCheckpointCommand){
            return new MgResolveCheckpointCommandTask(context, (MgLogicalCheckpointCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalTryCommand){
            return new MgResolveTryCommandTask(context, (MgLogicalTryCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalCatchCommand){
            return new MgResolveCatchCommandTask(context, (MgLogicalCatchCommand) logicalCommand);
        }

        if(logicalCommand instanceof MgLogicalFinallyCommand){
            return new MgResolveFinallyCommandTask(context, (MgLogicalFinallyCommand) logicalCommand);
        }

        throw new RuntimeException("Unsupported command " + logicalCommand.getClass().getSimpleName() + " for resolve.");
    }

    public MgResolveCommandTask() {
    }

    public abstract MgCommand getCommand();

// todo - remove if unused
//
//    protected static MgVariable singleOutput(List<MgVariable> output){
//        if(output.count() <= 0){
//            throw new LanguageException("Expression has no output value.");
//        } else if(output.count() == 1){
//            return output.getFirst();
//        } else {
//            throw new LanguageException("Expression has multiple output values.");
//        }
//    }
//
//    protected static MgVariable boolOutput(MgVariable variable){
//        if(variable.getDatatype().getType() == MgBoolObject.TYPE){
//            return variable;
//        } else {
//            throw new LanguageException("Expected " + MgBoolObject.TYPE.getName() + " output (buildin type), got " + variable.getDatatype().getType().getName());
//        }
//    }
//
//    protected static void noOutput(List<MgVariable> output){
//        if(output.count() > 0){
//            throw new LanguageException("Ignored expression output.");
//        }
//    }
}
