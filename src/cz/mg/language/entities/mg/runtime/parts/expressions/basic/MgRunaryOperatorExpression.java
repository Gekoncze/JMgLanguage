package cz.mg.language.entities.mg.runtime.parts.expressions.basic;

import cz.mg.collections.list.List;


public class MgRunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgRunaryOperatorExpression(MgBasicExpression expression, List<Replication> replications) {
        super(expression, replications);
    }
}
