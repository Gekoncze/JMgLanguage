package cz.mg.language.entities.mg.unresolved.parts.expressions.calls;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.text.ReadableText;


public class MgUnresolvedFunctionCallExpression extends MgUnresolvedCallExpression {
    @Mandatory @Value
    private final ReadableText name;

    @Optional @Part
    private final MgUnresolvedCallExpression expression;

    public MgUnresolvedFunctionCallExpression(ReadableText name) {
        this.name = name;
        this.expression = null;
    }

    public MgUnresolvedFunctionCallExpression(ReadableText name, MgUnresolvedCallExpression expression) {
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
