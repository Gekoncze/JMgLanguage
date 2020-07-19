package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgVariable;


public class VariableExpression extends Expression {
    @Link
    private MgVariable variable;

    public VariableExpression() {
    }

    public MgVariable getVariable() {
        return variable;
    }

    public void setVariable(MgVariable variable) {
        this.variable = variable;
    }
}
