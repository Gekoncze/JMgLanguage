package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgLunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgLunaryOperatorExpression(MgExpression expression, List<MgOperator> operators) {
        super(expression, operators);
    }
}
