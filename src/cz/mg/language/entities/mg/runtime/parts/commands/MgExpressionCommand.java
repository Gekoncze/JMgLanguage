package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
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
    public void run(MgFunctionInstanceImpl functionObject) {
        expression.run(functionObject);
    }
}
