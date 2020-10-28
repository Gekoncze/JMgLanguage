package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgRunaryOperator;


public class MgRunaryOperatorExpression extends MgUnaryOperatorExpression {
    public MgRunaryOperatorExpression(ReadableCollection<MgRunaryOperator> operators) {
        super(operators);
    }
}
