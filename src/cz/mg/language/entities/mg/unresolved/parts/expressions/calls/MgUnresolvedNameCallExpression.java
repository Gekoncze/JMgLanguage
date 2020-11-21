package cz.mg.language.entities.mg.unresolved.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;


public class MgUnresolvedNameCallExpression extends MgUnresolvedCallExpression {
    @Mandatory @Value
    private final ReadableText name;

    @Optional @Part
    private final MgUnresolvedCallExpression expression;

    public MgUnresolvedNameCallExpression(ReadableText name) {
        this.name = name;
        this.expression = null;
    }

    public MgUnresolvedNameCallExpression(ReadableText name, MgUnresolvedCallExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    public ReadableText getName() {
        return name;
    }

    public MgUnresolvedCallExpression getExpression() {
        return expression;
    }
}
