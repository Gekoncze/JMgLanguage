package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgBoolType;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgBoolObject;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.BreakException;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ContinueException;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgWhileCommand extends MgCommand implements Breakable, Continuable {
    private static final MgDatatype BOOL_DATATYPE = new MgDatatype(
        MgBoolType.getInstance(),
        MgDatatype.Storage.ANY,
        MgDatatype.Requirement.OPTIONAL
    );

    @Optional @Part
    private final ReadableText name;

    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Link
    private final MgInputConnector inputConnector = new MgInputConnector(BOOL_DATATYPE);

    @Mandatory @Part
    private final List<MgCommand> commands = new List<>();

    public MgWhileCommand(ReadableText name, MgExpression expression) {
        this.name = name;
        this.expression = expression;
        new Todo(); // todo - connect and validate
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public MgInputConnector getInputConnector() {
        return inputConnector;
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
        MgInstanceVariable conditionVariable = inputConnector.getConnection().getConnectionVariable();
        MgBoolObject condition = (MgBoolObject) functionObject.getObjects().get(conditionVariable.getCache().getOffset());
        return condition.getValue();
    }
}
