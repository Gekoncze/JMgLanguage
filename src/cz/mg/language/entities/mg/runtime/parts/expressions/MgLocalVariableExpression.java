package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;


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
    public void run(MgFunctionInstanceImpl functionObject) {
        // nothing to do
    }
}
