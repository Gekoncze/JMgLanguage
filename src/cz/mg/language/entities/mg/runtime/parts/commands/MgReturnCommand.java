package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ReturnException;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgReturnCommand extends MgCommand {
    @Mandatory @Part
    private final MgExpression expression;

    public MgReturnCommand(MgExpression expression) {
        this.expression = expression;
    }

    public MgExpression getExpression() {
        return expression;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        throw new ReturnException();
    }
}
