package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;


public class MgLogicalOperatorCallExpression extends MgLogicalFunctionCallExpression {
    public MgLogicalOperatorCallExpression(ReadableText name) {
        super(name);
    }

    public MgLogicalOperatorCallExpression(ReadableText name, List<MgLogicalCallExpression> expressions) {
        super(name, expressions);
    }
}
