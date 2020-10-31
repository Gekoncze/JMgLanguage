package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;


public class MgGlobalVariableSetExpression extends MgVariableExpression implements MgVariableSetExpression {
    public MgGlobalVariableSetExpression(MgGlobalVariable variable) {
        super(
            new Array<>(
                MgVariableSetExpression.createInputConnector(variable)
            ),
            new Array<>(),
            variable
        );
    }

    @Override
    public MgGlobalVariable getVariable() {
        return (MgGlobalVariable) super.getVariable();
    }

    @Override
    protected void onRun(MgFunctionInstance functionInstance) {
        MgInputConnector inputConnector = getInputConnectors().getLast();
        MgInstanceVariable inputVariable = inputConnector.getConnection().getConnectionVariable();
        MgObject inputObject = functionInstance.getObjects().get(inputVariable.getCache().getOffset());

        getVariable().setObject(inputObject);
    }
}
