package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.RollbackException;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.instances.MgInstance;


public class MgRollbackCommand extends MgCommand {
    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Value
    private final MgFunctionVariable input;

    public MgRollbackCommand(@Part MgExpression expression, MgFunctionVariable input) {
        this.expression = expression;
        this.input = input;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public MgFunctionVariable getInput() {
        return input;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        expression.run(functionInstance);
        throw new RollbackException((MgInstance) functionInstance.getObjects().get(input.getOffset()));
    }
}
