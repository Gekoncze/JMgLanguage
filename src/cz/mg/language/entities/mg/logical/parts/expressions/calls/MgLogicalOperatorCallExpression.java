package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;


public class MgLogicalOperatorCallExpression extends MgLogicalFunctionCallExpression {
    public MgLogicalOperatorCallExpression(ReadableText name) {
        super(name);
    }

    public MgLogicalOperatorCallExpression(ReadableText name, MgLogicalCallExpression expression) {
        super(name, new List<>(expression));
    }

    public MgLogicalOperatorCallExpression(ReadableText name, MgLogicalCallExpression left, MgLogicalCallExpression right) {
        super(name, new List<>(left, right));
    }
}
