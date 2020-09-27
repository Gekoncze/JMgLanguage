package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.command.utilities.VariableHelper;
import cz.mg.language.tasks.mg.resolver.command.utilities.OperatorCache;


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
    public void forEachComponent(ObjectVisitor visitor) {
        for(MgVariable variable : command.getDeclaredVariables()){
            visitor.onVisitComponent(variable, null);
        }
    }
}
