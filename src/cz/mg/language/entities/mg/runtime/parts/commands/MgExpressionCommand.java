package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgExpressionCommand extends MgCommand {
    @Mandatory @Part
    private final MgExpression expression;

    public MgExpressionCommand(MgExpression expression) {
        this.expression = expression;
    }

    public MgExpression getExpression() {
        return expression;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        expression.run(functionInstance);
    }
}
