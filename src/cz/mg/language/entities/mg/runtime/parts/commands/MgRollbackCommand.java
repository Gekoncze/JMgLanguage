package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgExceptionType;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgInstance;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.RollbackException;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgRollbackCommand extends MgCommand {
    private static final MgDatatype EXCEPTION_DATATYPE = new MgDatatype(
        MgExceptionType.getInstance(),
        MgDatatype.Storage.ANY,
        MgDatatype.Requirement.OPTIONAL
    );

    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Link
    private final MgInputConnector inputConnector = new MgInputConnector(EXCEPTION_DATATYPE);

    public MgRollbackCommand(MgExpression expression) {
        this.expression = expression;
        new Todo(); // todo - connect and validate
    }

    public MgExpression getExpression() {
        return expression;
    }

    public MgInputConnector getInputConnector() {
        return inputConnector;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        expression.run(functionInstance);
        MgInstanceVariable exceptionVariable = inputConnector.getConnection().getConnectionVariable();
        throw new RollbackException((MgInstance) functionInstance.getObjects().get(exceptionVariable.getCache().getOffset()));
    }
}
