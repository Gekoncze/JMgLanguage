package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;


public class MgLunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgLunaryOperatorExpression(MgExpression expression, List<Replication> replications) {
        super(expression, replications);
    }
}
