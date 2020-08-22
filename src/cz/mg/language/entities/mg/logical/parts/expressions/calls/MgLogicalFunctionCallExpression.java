package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalFunctionCallExpression extends MgLogicalCallExpression {
    @Value
    private final ReadableText name;

    @Part
    private final List<MgLogicalCallExpression> expressions = new List<>();

    public MgLogicalFunctionCallExpression(ReadableText name) {
        this.name = name;
    }

    public ReadableText getName() {
        return name;
    }

    public List<MgLogicalCallExpression> getExpressions() {
        return expressions;
    }
}
