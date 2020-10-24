package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;


public class MgLogicalNameCallExpression extends MgLogicalCallExpression {
    @Mandatory @Value
    private final ReadableText name;

    @Optional @Part
    private final MgLogicalCallExpression expression;

    public MgLogicalNameCallExpression(ReadableText name) {
        this.name = name;
        this.expression = null;
    }

    public MgLogicalNameCallExpression(ReadableText name, MgLogicalCallExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    public ReadableText getName() {
        return name;
    }

    public MgLogicalCallExpression getExpression() {
        return expression;
    }
}
