package cz.mg.language.tasks.mg.resolver.context.executable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.command.utilities.OperatorCache;
import cz.mg.language.tasks.mg.resolver.context.component.structured.FunctionContext;


public class CommandContext extends ExecutableContext {
    @Optional @Link
    private MgCommand command;

    public CommandContext(@Mandatory ExecutableContext outerContext) {
        super(outerContext);
    }

    @Override
    public @Optional ExecutableContext getOuterContext() {
        return (ExecutableContext) super.getOuterContext();
    }

    public MgCommand getCommand() {
        return command;
    }

    public void setCommand(MgCommand command) {
        this.command = command;
    }

    public FunctionContext getFunctionContext(){
        if(getOuterContext() instanceof CommandContext){
            return ((CommandContext) getOuterContext()).getFunctionContext();
        } else if(getOuterContext() instanceof FunctionBodyContext) {
            return (FunctionContext) getOuterContext().getOuterContext();
        } else {
            throw new RuntimeException();
        }
    }

    public OperatorCache getOperatorCache() {
        return getFunctionContext().getOperatorCache();
    }
}
