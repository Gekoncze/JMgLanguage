package cz.mg.language.entities.mg.logical.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;


public abstract class MgLogicalUnaryOperatorCallExpression extends MgLogicalOperatorCallExpression {
    public MgLogicalUnaryOperatorCallExpression(ReadableText name) {
        super(name);
    }
}
