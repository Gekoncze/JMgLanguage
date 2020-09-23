package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgLocalVariableExpression extends MgExpression {
    @Mandatory @Link
    private final MgLocalVariable variable;

    public MgLocalVariableExpression(MgLocalVariable variable) {
        this.variable = variable;
    }

    public MgLocalVariable getVariable() {
        return variable;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // nothing to do
    }
}
