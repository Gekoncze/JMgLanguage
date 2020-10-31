package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgGlobalVariableGetExpression extends MgVariableExpression implements MgVariableGetExpression {
    public MgGlobalVariableGetExpression(MgGlobalVariable variable) {
        super(
            new Array<>(),
            new Array<>(
                MgVariableGetExpression.createOutputConnector(variable)
            ),
            variable
        );
    }

    @Override
    public MgGlobalVariable getVariable() {
        return (MgGlobalVariable) super.getVariable();
    }

    @Override
    protected void onRun(MgFunctionInstance functionInstance) {
        MgOutputConnector outputConnector = getOutputConnectors().getFirst();
        MgInstanceVariable outputVariable = outputConnector.getConnection().getConnectionVariable();
        MgObject outputObject = getVariable().getObject();

        functionInstance.getObjects().set(outputObject, outputVariable.getCache().getOffset());
    }
}
