package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.parts.expressions.MgExpressionL;


public class MgWhileCommandL extends MgBlockCommandL {
    @Part
    private final MgExpressionL expression;

    @Value
    private final ReadableText name;

    public MgWhileCommandL(MgExpressionL expression) {
        this(expression, null);
    }

    public MgWhileCommandL(MgExpressionL expression, ReadableText name) {
        this.expression = expression;
        this.name = name;
    }

    public MgExpressionL getExpression() {
        return expression;
    }
}
