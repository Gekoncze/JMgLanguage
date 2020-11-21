package cz.mg.language.entities.mg.unresolved.parts.expressions.calls.operator;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.mg.unresolved.parts.expressions.calls.MgUnresolvedCallExpression;


public abstract class MgUnresolvedOperatorCallExpression extends MgUnresolvedCallExpression {
    @Value
    private final ReadableText name;

    public MgUnresolvedOperatorCallExpression(ReadableText name) {
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }
}
