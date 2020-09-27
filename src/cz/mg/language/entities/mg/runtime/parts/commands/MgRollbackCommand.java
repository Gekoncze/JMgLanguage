package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.RollbackException;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.roles.MgInstance;


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
    public void run(MgFunctionInstanceImpl functionObject) {
        expression.run(functionObject);
        throw new RollbackException((MgInstance) functionObject.getObjects().get(input.getOffset()));
    }
}
