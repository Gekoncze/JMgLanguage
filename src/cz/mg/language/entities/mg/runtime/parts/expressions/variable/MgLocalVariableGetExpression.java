package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public class MgLocalVariableGetExpression extends MgVariableExpression implements MgVariableGetExpression {
    public MgLocalVariableGetExpression(MgInstanceVariable variable) {
        super(
            new Array<>(),
            new Array<>(
                MgVariableGetExpression.createOutputConnector(variable)
            ),
            variable
        );
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    public void onRun(MgFunctionInstance functionInstance) {
    }
}
