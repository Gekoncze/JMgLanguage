package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;


public class MgRunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgRunaryOperatorExpression(MgExpression expression, List<Replication> replications) {
        super(expression, replications);
    }
}
