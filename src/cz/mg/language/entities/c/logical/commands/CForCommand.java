package cz.mg.language.entities.c.logical.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.c.logical.parts.expressions.CExpression;


public class CForCommand extends CBlockCommand {
    @Part
    private CExpression initializer;

    @Part
    private CExpression condition;

    @Part
    private CExpression modifier;

    public CForCommand(CExpression initializer, CExpression condition, CExpression modifier, CCommand... commands) {
        super(commands);
        this.initializer = initializer;
        this.condition = condition;
        this.modifier = modifier;
    }

    public CExpression getInitializer() {
        return initializer;
    }

    public CExpression getCondition() {
        return condition;
    }

    public CExpression getModifier() {
        return modifier;
    }
}
