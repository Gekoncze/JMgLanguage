package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgVariableExpression extends MgExpression {
    public MgVariableExpression() {
    }

    public abstract MgInstanceVariable getVariable();
    public abstract MgStructuredInstance getParent(MgFunctionInstance functionInstance);
}
