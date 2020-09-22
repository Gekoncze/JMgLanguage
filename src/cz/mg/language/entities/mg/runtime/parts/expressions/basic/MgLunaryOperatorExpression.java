package cz.mg.language.entities.mg.runtime.parts.expressions.basic;

import cz.mg.collections.list.List;


public class MgLunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgLunaryOperatorExpression(MgBasicExpression expression, List<Replication> replications) {
        super(expression, replications);
    }
}
