package cz.mg.language.entities.mg.unresolved.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.unresolved.parts.expressions.MgUnresolvedClumpExpression;


public class MgUnresolvedIfCommand extends MgUnresolvedBlockCommand {
    @Optional @Part
    private MgUnresolvedClumpExpression expression;

    public MgUnresolvedIfCommand() {
    }

    public MgUnresolvedIfCommand(MgUnresolvedClumpExpression expression) {
        this.expression = expression;
    }

    public MgUnresolvedClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgUnresolvedClumpExpression expression) {
        this.expression = expression;
    }
}
