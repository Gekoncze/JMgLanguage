package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.unresolved.parts.expressions.MgUnresolvedClumpExpression;


public class MgUnresolvedWhileCommand extends MgUnresolvedBlockCommand {
    @Part
    private MgUnresolvedClumpExpression expression;

    public MgUnresolvedWhileCommand() {
    }

    public MgUnresolvedWhileCommand(MgUnresolvedClumpExpression expression) {
        this.expression = expression;
    }

    public MgUnresolvedClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgUnresolvedClumpExpression expression) {
        this.expression = expression;
    }
}
