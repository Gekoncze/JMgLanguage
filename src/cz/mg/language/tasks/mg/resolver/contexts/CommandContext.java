package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.Context;


public class CommandContext extends Context {
    @Optional @Link
    private MgCommand command;

    public CommandContext(CommandContext outerContext) {
        super(notNull(outerContext));
    }

    public CommandContext(FunctionContext outerContext) {
        super(notNull(outerContext));
    }

    public MgCommand getCommand() {
        return command;
    }

    public void setCommand(MgCommand command) {
        this.command = command;
    }

    public OperatorCache getOperatorCache() {
        if(getOuterContext() instanceof CommandContext){
            return ((CommandContext) getOuterContext()).getOperatorCache();
        } else if(getOuterContext() instanceof FunctionContext) {
            return ((FunctionContext) getOuterContext()).getOperatorCache();
        } else {
            throw new RuntimeException();
        }
    }

    public MgFunction getFunction(){
        if(getOuterContext() instanceof CommandContext){
            return ((CommandContext) getOuterContext()).getFunction();
        } else if(getOuterContext() instanceof FunctionContext) {
            return ((FunctionContext) getOuterContext()).getFunction();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Iterable<? extends MgComponent> read() {
        return null; // todo - return a list of accessible local variables
    }

    private static Context notNull(Context context){
        if(context == null) throw new IllegalArgumentException();
        else return context;
    }
}
