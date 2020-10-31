package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgInstanceVariableGetExpression extends MgVariableExpression implements MgVariableGetExpression {
    public MgInstanceVariableGetExpression(MgInstanceVariable variable) {
        super(
            new Array<>(
                MgInstanceVariableExpression.createInputConnector(variable)
            ),
            new Array<>(
                MgVariableGetExpression.createOutputConnector(variable)
            ),
            variable
        );
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    protected void onRun(MgFunctionInstance functionInstance) {
        MgInputConnector targetConnector = getInputConnectors().getFirst();
        MgInstanceVariable targetVariable = targetConnector.getConnection().getConnectionVariable();
        MgStructuredInstance targetObject = (MgStructuredInstance) functionInstance.getObjects().get(targetVariable.getCache().getOffset());

        MgOutputConnector outputConnector = getOutputConnectors().getFirst();
        MgInstanceVariable outputVariable = outputConnector.getConnection().getConnectionVariable();
        MgObject outputObject = targetObject.getObjects().get(getVariable().getCache().getOffset());

        functionInstance.getObjects().set(outputObject, outputVariable.getCache().getOffset());
    }
}
