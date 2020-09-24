package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgWhileCommand extends MgCommand implements Breakable, Continuable {
    @Optional @Part
    private final ReadableText name;

    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Value
    private final int input;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgWhileCommand(ReadableText name, MgExpression expression, int input, List<MgCommand> commands) {
        this.name = name;
        this.expression = expression;
        this.input = input;
        this.commands = commands;
    }

    @Override
    public ReadableText getName() {
        return name;
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
        while(true){
            expression.run(functionObject);
            MgBoolObject condition = (MgBoolObject) functionObject.getObjects().get(input);
            if(condition.getValue()){
                for(MgCommand command : commands){
                    command.run(functionObject);
                }
            } else {
                return;
            }
        }
    }
}
