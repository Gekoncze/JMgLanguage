package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.RollbackException;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgRollbackCommand extends MgCommand {
    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Value
    private final MgLocalVariable input;

    public MgRollbackCommand(@Part MgExpression expression, MgLocalVariable input) {
        this.expression = expression;
        this.input = input;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public MgLocalVariable getInput() {
        return input;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        expression.run(functionObject);
        throw new RollbackException(functionObject.getObjects().get(input.getOffset()));
    }
}
