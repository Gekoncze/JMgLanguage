package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;


public class MgLogicalFunctionCallExpression extends MgLogicalCallExpression {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Part
    private final MgLogicalCallExpression expression;

    public MgLogicalFunctionCallExpression(ReadableText name, MgLogicalCallExpression expression) {
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
