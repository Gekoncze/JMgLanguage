package cz.mg.language.entities.logic.mg.commands;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.expressions.MgExpression;


public class MgWhileCommand extends MgBlockCommand {
    @Part
    private final MgExpression expression;

    @Value
    private final ReadableText name;

    public MgWhileCommand(MgExpression expression) {
        this(expression, null);
    }

    public MgWhileCommand(MgExpression expression, ReadableText name) {
        this.expression = expression;
        this.name = name;
    }

    public MgExpression getExpression() {
        return expression;
    }
}
