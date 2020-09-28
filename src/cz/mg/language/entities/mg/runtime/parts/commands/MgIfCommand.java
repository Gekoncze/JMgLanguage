package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgBoolObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgIfCommand extends MgCommand {
    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Value
    private final MgFunctionVariable input;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgIfCommand(MgExpression expression, MgFunctionVariable input, List<MgCommand> commands) {
        this.expression = expression;
        this.input = input;
        this.commands = commands;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public MgFunctionVariable getInput() {
        return input;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        expression.run(functionObject);
        MgBoolObject condition = (MgBoolObject) functionObject.getObjects().get(input.getOffset());
        if(condition.getValue()){
            for(MgCommand command : commands){
                command.run(functionObject);
            }
        }
    }
}
