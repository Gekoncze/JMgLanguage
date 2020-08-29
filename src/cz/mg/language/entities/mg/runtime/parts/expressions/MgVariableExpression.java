package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgVariableExpression extends MgMemberAccessibleExpression {
    @Mandatory @Value
    private final int input;

    @Mandatory @Value
    private final int output;

    public MgVariableExpression(int input, int output) {
        this.input = input;
        this.output = output;
    }

    public int getInput() {
        return input;
    }

    public int getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        functionObject.getObjects().set(functionObject.getObjects().get(input), output);
    }

    @Override
    public void run(MgFunctionObject functionObject, MgClassObject classObject){
        functionObject.getObjects().set(classObject.getObjects().get(input), output);
    }
}
