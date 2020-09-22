package cz.mg.language.entities.mg.runtime.parts.expressions.basic;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.member.MgMemberExpression;


public class MgMemberAccessExpression extends MgBasicExpression {
    @Mandatory @Part
    private final MgBasicExpression leftExpression;

    @Mandatory @Part
    private final MgMemberExpression rightExpression;

    @Mandatory @Part
    private final MgLocalVariable leftInput;

    @Mandatory @Part
    private final MgLocalVariable rightInput;

    @Mandatory @Part
    private final MgLocalVariable output;

    public MgMemberAccessExpression(
        MgBasicExpression leftExpression,
        MgMemberExpression rightExpression,
        MgLocalVariable leftInput,
        MgLocalVariable rightInput,
        MgLocalVariable output
    ) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.output = output;
    }

    public MgExpression getLeftExpression() {
        return leftExpression;
    }

    public MgMemberExpression getRightExpression() {
        return rightExpression;
    }

    public MgLocalVariable getLeftInput() {
        return leftInput;
    }

    public MgLocalVariable getRightInput() {
        return rightInput;
    }

    public MgLocalVariable getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        leftExpression.run(functionObject);
        MgObject left = functionObject.getObjects().get(leftInput.getOffset());
        rightExpression.run(functionObject, (MgClassObject) left);
        functionObject.getObjects().set(functionObject.getObjects().get(rightInput.getOffset()), output.getOffset());
    }
}
