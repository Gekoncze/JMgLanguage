package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgVariableExpression extends MgExpression {
    @Mandatory @Link
    private final MgVariable variable;

    public MgVariableExpression(MgVariable variable) {
        this.variable = variable;
    }

    public MgVariable getVariable() {
        return variable;
    }
}
