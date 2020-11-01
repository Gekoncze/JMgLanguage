package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ReturnException;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgReturnCommand extends MgCommand {
    @Mandatory @Part
    private final MgExpression expression;

    public MgReturnCommand(MgExpression expression) {
        this.expression = expression;
        new Todo(); // todo - connect and validate
    }

    public MgExpression getExpression() {
        return expression;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        throw new ReturnException();
    }
}
