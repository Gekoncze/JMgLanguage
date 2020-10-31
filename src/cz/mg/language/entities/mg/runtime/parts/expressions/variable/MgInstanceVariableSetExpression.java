package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;


public class MgInstanceVariableSetExpression extends MgVariableExpression implements MgVariableSetExpression {
    public MgInstanceVariableSetExpression(MgInstanceVariable variable) {
        super(
            new Array<>(
                MgInstanceVariableExpression.createInputConnector(variable),
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
        MgInputConnector targetConnector = getInputConnectors().getFirst();
        MgInstanceVariable targetVariable = targetConnector.getConnection().getConnectionVariable();
        MgStructuredInstance targetObject = (MgStructuredInstance) functionInstance.getObjects().get(targetVariable.getCache().getOffset());

        MgInputConnector inputConnector = getInputConnectors().getLast();
        MgInstanceVariable inputVariable = inputConnector.getConnection().getConnectionVariable();
        MgObject inputObject = functionInstance.getObjects().get(inputVariable.getCache().getOffset());

        targetObject.getObjects().set(inputObject, getVariable().getCache().getOffset());
    }
}
