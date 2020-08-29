package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgMemberExpression extends MgExpression {
    @Mandatory @Part
    private final MgExpression leftExpression;

    @Mandatory @Part
    private final MgMemberAccessibleExpression rightExpression;

    @Mandatory @Part
    private final int leftInput;

    @Mandatory @Part
    private final int rightInput;

    @Mandatory @Part
    private final int output;

    public MgMemberExpression(MgExpression leftExpression, MgMemberAccessibleExpression rightExpression, int leftInput, int rightInput, int output) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.output = output;
    }

    public MgExpression getLeftExpression() {
        return leftExpression;
    }

    public MgMemberAccessibleExpression getRightExpression() {
        return rightExpression;
    }

    public int getLeftInput() {
        return leftInput;
    }

    public int getRightInput() {
        return rightInput;
    }

    public int getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        leftExpression.run(functionObject);
        MgObject left = functionObject.getObjects().get(leftInput);
        rightExpression.run(functionObject, (MgClassObject) left);
        functionObject.getObjects().set(functionObject.getObjects().get(rightInput), output);
    }
}
