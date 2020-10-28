package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgLunaryOperator;


public class MgLunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgLunaryOperatorExpression(ReadableCollection<MgLunaryOperator> operators) {
        super(operators);
    }
}
