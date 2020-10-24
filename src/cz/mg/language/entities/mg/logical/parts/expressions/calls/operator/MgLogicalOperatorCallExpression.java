package cz.mg.language.entities.mg.logical.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.logical.parts.expressions.calls.MgLogicalCallExpression;


public abstract class MgLogicalOperatorCallExpression extends MgLogicalCallExpression {
    @Value
    private final ReadableText name;

    public MgLogicalOperatorCallExpression(ReadableText name) {
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }
}
