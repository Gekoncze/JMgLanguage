package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgLunaryOperator;


public class MgLunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgLunaryOperatorExpression(ReadableCollection<? extends MgReplication> replications) {
        super(replications);
    }

    public static class MgReplication extends MgUnaryOperatorExpression.MgReplication {
        public MgReplication(MgLunaryOperator operator) {
            super(operator);
        }
    }
}
