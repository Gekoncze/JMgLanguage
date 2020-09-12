package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgMemberVariableExpression extends MgMemberAccessibleExpression {
    @Mandatory @Value
    private final MgMemberVariable variable;

    @Mandatory @Value
    private final int output;

    public MgMemberVariableExpression(MgMemberVariable variable, int output) {
        this.variable = variable;
        this.output = output;
    }

    public MgMemberVariable getVariable() {
        return variable;
    }

    public int getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        throw new RuntimeException();
    }

    @Override
    public void run(MgFunctionObject functionObject, MgClassObject classObject){
        functionObject.getObjects().set(classObject.getObjects().get(variable.getOffset()), output);
    }
}
