package cz.mg.language.tasks.mg.resolver.contexts;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.Context;
import cz.mg.language.tasks.mg.resolver.VariableHelper;
import cz.mg.language.tasks.mg.resolver.contexts.utilities.OperatorCache;


public class CommandContext extends Context {
    @Optional @Link
    private MgCommand command;

    public CommandContext(@Mandatory CommandContext outerContext) {
        super(outerContext);
    }

    public CommandContext(@Mandatory FunctionContext outerContext) {
        super(outerContext);
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
        } else if(getOuterContext() instanceof FunctionContext) {
            return (FunctionContext) getOuterContext();
        } else {
            throw new RuntimeException();
        }
    }

    public OperatorCache getOperatorCache() {
        return getFunctionContext().getOperatorCache();
    }

    public VariableHelper getVariableHelper(){
        return getFunctionContext().getVariableHelper();
    }

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
        for(MgComponent variable : command.getDeclaredVariables()){
            visitor.onVisitComponent(variable, null);
        }
    }
}
