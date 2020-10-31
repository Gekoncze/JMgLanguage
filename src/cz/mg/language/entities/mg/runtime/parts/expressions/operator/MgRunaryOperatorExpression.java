package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgRunaryOperator;


public class MgRunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgRunaryOperatorExpression(ReadableCollection<? extends MgReplication> replications) {
        super(replications);
    }

    public static class MgReplication extends MgUnaryOperatorExpression.MgReplication {
        public MgReplication(MgRunaryOperator operator) {
            super(operator);
        }
    }
}
