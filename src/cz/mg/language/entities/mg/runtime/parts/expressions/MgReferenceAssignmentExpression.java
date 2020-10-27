package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgReferenceAssignmentExpression extends MgExpression {
    public MgReferenceAssignmentExpression() {
        super(new Array<>(), new Array<>());
    }

    @Override
    protected void onRunExpressions(MgFunctionInstance functionInstance) {
        getExpressions().getLast().run(functionInstance);
        getExpressions().getFirst().run(functionInstance);
    }

    @Override
    protected void onRun(MgFunctionInstance functionInstance) {
    }
}
