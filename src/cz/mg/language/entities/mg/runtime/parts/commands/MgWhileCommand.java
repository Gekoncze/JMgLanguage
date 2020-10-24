package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgBoolObject;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.BreakException;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ContinueException;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgWhileCommand extends MgCommand implements Breakable, Continuable {
    @Optional @Part
    private final ReadableText name;

    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Value
    private final MgFunctionVariable input;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgWhileCommand(ReadableText name, MgExpression expression, MgFunctionVariable input, List<MgCommand> commands) {
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

    public MgFunctionVariable getInput() {
        return input;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        while(evaluateExpression(functionInstance)){
            try {
                for(MgCommand command : commands){
                    command.run(functionInstance);
                }
            } catch (BreakException e){
                if(e.getTarget() == this){
                    break;
                } else {
                    throw e;
                }
            } catch (ContinueException e){
                if(e.getTarget() == this){
                    continue;
                } else {
                    throw e;
                }
            }
        }
    }

    private boolean evaluateExpression(MgFunctionInstance functionObject){
        expression.run(functionObject);
        MgBoolObject condition = (MgBoolObject) functionObject.getObjects().get(input.getOffset());
        return condition.getValue();
    }
}
