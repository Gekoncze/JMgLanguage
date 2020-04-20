package cz.mg.compiler.entities.logic.c.commands;

import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.entities.logic.c.parts.expressions.CExpression;


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
