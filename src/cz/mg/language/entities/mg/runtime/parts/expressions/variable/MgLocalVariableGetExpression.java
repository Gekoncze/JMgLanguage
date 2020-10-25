package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public class MgLocalVariableGetExpression extends MgVariableGetExpression {
    public MgLocalVariableGetExpression(MgInstanceVariable variable) {
        super(variable);
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    protected MgCache createCache() {
        return new MgCache(
            new Array<>(),
            new Array<>(getOutputConnector())
        );
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();
        // nothing to do
    }
}
