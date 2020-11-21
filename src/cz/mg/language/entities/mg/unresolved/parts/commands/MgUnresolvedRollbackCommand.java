package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.unresolved.parts.expressions.MgUnresolvedClumpExpression;


public class MgUnresolvedRollbackCommand extends MgUnresolvedCommand {
    @Part
    private MgUnresolvedClumpExpression expression;

    public MgUnresolvedRollbackCommand() {
    }

    public MgUnresolvedRollbackCommand(MgUnresolvedClumpExpression expression) {
        this.expression = expression;
    }

    public MgUnresolvedClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgUnresolvedClumpExpression expression) {
        this.expression = expression;
    }
}
