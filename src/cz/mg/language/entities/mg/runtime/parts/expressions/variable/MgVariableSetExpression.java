package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;


public abstract class MgVariableSetExpression extends MgVariableExpression {
    @Mandatory @Part
    private final MgInputConnector inputConnector;

    public MgVariableSetExpression(MgVariable variable) {
        super(variable);
        this.inputConnector = new MgInputConnector(variable.getDatatype());
    }

    public MgInputConnector getInputConnector() {
        return inputConnector;
    }
}
