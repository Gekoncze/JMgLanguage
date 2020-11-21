package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.unresolved.parts.expressions.MgUnresolvedClumpExpression;


public class MgUnresolvedExpressionCommand extends MgUnresolvedCommand {
    @Part
    private MgUnresolvedClumpExpression expression;

    public MgUnresolvedExpressionCommand() {
    }

    public MgUnresolvedExpressionCommand(MgUnresolvedClumpExpression expression) {
        this.expression = expression;
    }

    public MgUnresolvedClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgUnresolvedClumpExpression expression) {
        this.expression = expression;
    }
}
