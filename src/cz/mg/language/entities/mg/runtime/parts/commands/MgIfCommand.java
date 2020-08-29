package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgIfCommand extends MgCommand {
    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Value
    private final int input;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgIfCommand(MgExpression expression, int input, List<MgCommand> commands) {
        this.expression = expression;
        this.input = input;
        this.commands = commands;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public int getInput() {
        return input;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        expression.run(functionObject);
        MgBoolObject condition = (MgBoolObject) functionObject.getObjects().get(input);
        if(condition.getValue()){
            for(MgCommand command : commands){
                command.run(functionObject);
            }
        }
    }
}
