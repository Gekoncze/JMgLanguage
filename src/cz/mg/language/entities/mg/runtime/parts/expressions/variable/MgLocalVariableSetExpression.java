package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;


public class MgLocalVariableSetExpression extends MgVariableExpression {
    public MgLocalVariableSetExpression(MgInstanceVariable variable) {
        super(
            new Array<>(
                MgVariableSetExpression.createInputConnector(variable)
            ),
            new Array<>(),
            variable
        );
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    public void onRun(MgFunctionInstance functionInstance) {
        MgInputConnector inputConnector = getInputConnectors().getFirst();
        MgInstanceVariable inputVariable = inputConnector.getConnection().getConnectionVariable();
        MgObject inputObject = functionInstance.getObjects().get(inputVariable.getCache().getOffset());

        functionInstance.getObjects().set(inputObject, getVariable().getCache().getOffset());
    }
}
