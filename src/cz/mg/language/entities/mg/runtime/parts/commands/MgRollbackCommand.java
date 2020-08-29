package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.RollbackException;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgRollbackCommand extends MgCommand {
    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Value
    private final int input;

    public MgRollbackCommand(MgExpression expression, int input) {
        this.expression = expression;
        this.input = input;
    }

    public MgExpression getExpression() {
        return expression;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        expression.run(functionObject);
        throw new RollbackException(functionObject.getObjects().get(input));
    }
}
