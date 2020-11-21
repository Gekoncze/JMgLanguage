package cz.mg.language.entities.mg.unresolved.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;


public abstract class MgUnresolvedUnaryOperatorCallExpression extends MgUnresolvedOperatorCallExpression {
    public MgUnresolvedUnaryOperatorCallExpression(ReadableText name) {
        super(name);
    }
}
