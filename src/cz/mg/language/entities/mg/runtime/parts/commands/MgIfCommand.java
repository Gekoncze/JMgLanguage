package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.storage.Link;
import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgBoolType;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgBoolObject;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgIfCommand extends MgCommand {
    private static final MgDatatype BOOL_DATATYPE = new MgDatatype(
        MgBoolType.getInstance(),
        MgDatatype.Storage.ANY,
        MgDatatype.Requirement.OPTIONAL
    );

    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Link
    private final MgInputConnector inputConnector = new MgInputConnector(BOOL_DATATYPE);

    @Mandatory @Part
    private final List<@Mandatory @Part MgCommand> commands = new List<>();

    public MgIfCommand(MgExpression expression) {
        this.expression = expression;
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
        expression.run(functionInstance);
        MgInstanceVariable conditionVariable = inputConnector.getConnection().getConnectionVariable();
        MgBoolObject condition = (MgBoolObject) functionInstance.getObjects().get(conditionVariable.getCache().getOffset());
        if(condition.getValue()){
            for(MgCommand command : commands){
                command.run(functionInstance);
            }
        }
    }
}
