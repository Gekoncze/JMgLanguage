package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.logical.parts.commands.*;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.tasks.mg.resolver.MgResolverTask;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


public abstract class MgResolveCommandTask extends MgResolverTask {
    public static MgResolveCommandTask create(CommandContext context, MgLogicalCommand logicalCommand){
        if(logicalCommand instanceof MgLogicalExpressionCommand){
            return new MgResolveExpressionCommandTask(context, (MgLogicalExpressionCommand) logicalCommand);
        } else if(logicalCommand instanceof MgLogicalIfCommand){
            return new MgResolveIfCommandTask(context, (MgLogicalIfCommand) logicalCommand);
        } else if(logicalCommand instanceof MgLogicalElseIfCommand){
            return new MgResolveElseIfCommandTask(context, (MgLogicalElseIfCommand) logicalCommand);
        } else if(logicalCommand instanceof MgLogicalElseCommand){
            return new MgResolveElseCommandTask(context, (MgLogicalElseCommand) logicalCommand);
        } else if(logicalCommand instanceof MgLogicalWhileCommand){
            return new MgResolveWhileCommandTask(context, (MgLogicalWhileCommand) logicalCommand);
        } else if(logicalCommand instanceof MgLogicalContinueCommand){
            return new MgResolveContinueCommandTask(context, (MgLogicalContinueCommand) logicalCommand);
        } else if(logicalCommand instanceof MgLogicalBreakCommand){
            return new MgResolveBreakCommandTask(context, (MgLogicalBreakCommand) logicalCommand);
        } else if(logicalCommand instanceof MgLogicalReturnCommand){
            return new MgResolveReturnCommandTask(context, (MgLogicalReturnCommand) logicalCommand);
        } else {
            throw new RuntimeException("Unsupported command " + logicalCommand.getClass().getSimpleName() + " for resolve.");
        }
    }

    public MgResolveCommandTask() {
    }

    public abstract Command getCommand();

    protected static MgVariable singleOutput(List<MgVariable> output){
        if(output.count() <= 0){
            throw new LanguageException("Expression has no output value.");
        } else if(output.count() == 1){
            return output.getFirst();
        } else {
            throw new LanguageException("Expression has multiple output values.");
        }
    }

    protected static MgVariable boolOutput(MgVariable variable){
        if(variable.getDatatype().getType() == MgBoolObject.TYPE){
            return variable;
        } else {
            throw new LanguageException("Expected " + MgBoolObject.TYPE.getName() + " output (buildin type), got " + variable.getDatatype().getType().getName());
        }
    }

    protected static void noOutput(List<MgVariable> output){
        if(output.count() > 0){
            throw new LanguageException("Ignored expression output.");
        }
    }
}
