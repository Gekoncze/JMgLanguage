package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public abstract class MgVariableGetExpression extends MgVariableExpression {
    @Mandatory @Part
    private final MgOutputConnector outputConnector;

    public MgVariableGetExpression(MgVariable variable) {
        super(variable);
        this.outputConnector = new MgOutputConnector(variable.getDatatype());
    }

    public MgOutputConnector getOutputConnector() {
        return outputConnector;
    }
}
