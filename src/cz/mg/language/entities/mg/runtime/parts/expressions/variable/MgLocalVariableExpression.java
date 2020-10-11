package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;


public class MgLocalVariableExpression extends MgVariableExpression {
    @Mandatory @Link
    private final MgFunctionVariable variable;

    public MgLocalVariableExpression(MgFunctionVariable variable) {
        this.variable = variable;
    }

    @Override
    public MgFunctionVariable getVariable() {
        return variable;
    }

    @Override
    public MgStructuredInstance getParent(MgFunctionInstance functionInstance) {
        return functionInstance;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        // nothing to do
    }
}
