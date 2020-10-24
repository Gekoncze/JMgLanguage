package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.ReadableArray;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgVariableExpression extends MgExpression {
    @Mandatory @Link
    private final MgVariable variable;

    public MgVariableExpression(
        ReadableArray<MgInputConnector> inputConnectors,
        ReadableArray<MgOutputConnector> outputConnectors,
        MgVariable variable
    ) {
        super(inputConnectors, outputConnectors);
        this.variable = variable;
    }

    public MgVariable getVariable() {
        return variable;
    }
}
