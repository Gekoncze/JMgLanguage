package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.commands.MgLogicalBreakCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.Breakable;
import cz.mg.language.entities.mg.runtime.parts.commands.MgBreakCommand;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.context.Context;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class MgResolveBreakCommandTask extends MgResolveCommandTask {
    @Input
    private final CommandContext context;

    @Input
    private final MgLogicalBreakCommand logicalCommand;

    @Output
    private MgBreakCommand command;

    public MgResolveBreakCommandTask(CommandContext context, MgLogicalBreakCommand logicalCommand) {
        this.context = new CommandContext(context);
        this.logicalCommand = logicalCommand;
    }

    @Override
    public MgBreakCommand getCommand() {
        return command;
    }

    @Override
    protected void onRun() {
        command = new MgBreakCommand(findTargetCommand(logicalCommand.getTarget()));
        context.setCommand(command);
    }

    private MgCommand findTargetCommand(ReadableText name){
        Context current = context;
        while(current != null){
            if(current instanceof CommandContext){
                MgCommand command = ((CommandContext) current).getCommand();
                if(command instanceof Breakable){
                    if(name != null){
                        if(name.equals(((Breakable) command).getName())){
                            return command;
                        }
                    } else {
                        return command;
                    }
                }
            }
            current = current.getOuterContext();
        }

        throw new LanguageException("Could not find command to break.");
    }
}
