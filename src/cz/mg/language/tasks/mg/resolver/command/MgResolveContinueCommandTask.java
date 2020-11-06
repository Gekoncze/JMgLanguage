package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalContinueCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.Continuable;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgContinueCommand;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.executable.CommandContext;


public class MgResolveContinueCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalContinueCommand logicalCommand;

    @Output
    private MgContinueCommand command;

    public MgResolveContinueCommandTask(CommandContext context, MgLogicalContinueCommand logicalCommand) {
        this.context = new CommandContext(context);
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgContinueCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        command = new MgContinueCommand(findTargetCommand(logicalCommand.getTarget()));
        context.setCommand(command);
    }
    
    private MgCommand findTargetCommand(ReadableText name) {
        Context current = context;
        while(current != null){
            if(current instanceof CommandContext){
                MgCommand command = ((CommandContext) current).getCommand();
                if(command instanceof Continuable){
                    if(name != null){
                        if(name.equals(((Continuable) command).getName())){
                            return command;
                        }
                    } else {
                        return command;
                    }
                }
            }
            current = current.getOuterContext();
        }

        throw new LanguageException("Could not find command to continue.");
    }
}
