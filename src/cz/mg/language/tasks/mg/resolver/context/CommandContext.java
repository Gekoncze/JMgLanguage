package cz.mg.language.tasks.mg.resolver.context;

import cz.mg.annotations.storage.Link;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;
import cz.mg.language.tasks.mg.resolver.command.utilities.OperatorCache;
import cz.mg.language.tasks.mg.resolver.context.component.structured.FunctionContext;


public class CommandContext extends Context {
    @Optional @Link
    private MgCommand command;

    @Mandatory @Part
    private final List<@Mandatory @Link MgInstanceVariable> declaredVariables = new List<>();

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

    public List<MgInstanceVariable> getDeclaredVariables() {
        return declaredVariables;
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

    @Override
    public void forEachComponent(ComponentVisitor visitor) {
        for(MgVariable variable : declaredVariables){
            visitor.onVisitComponent(variable, null);
        }
    }
}
