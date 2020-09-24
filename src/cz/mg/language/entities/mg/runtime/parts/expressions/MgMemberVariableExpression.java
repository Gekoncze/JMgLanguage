package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgMemberVariableExpression extends MgExpression {
    @Mandatory @Part
    private final MgExpression target;

    @Mandatory @Link
    private final MgMemberVariable variable;

    @Mandatory @Link
    private final MgLocalVariable input;

    @Mandatory @Link
    private final MgLocalVariable output;

    public MgMemberVariableExpression(
        MgExpression target,
        MgMemberVariable variable,
        MgLocalVariable input,
        MgLocalVariable output
    ) {
        this.target = target;
        this.variable = variable;
        this.input = input;
        this.output = output;
    }

    public MgExpression getTarget() {
        return target;
    }

    public MgMemberVariable getVariable() {
        return variable;
    }

    public MgLocalVariable getInput() {
        return input;
    }

    public MgLocalVariable getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject){
        target.run(functionObject);
        MgClassObject classObject = (MgClassObject) functionObject.getObjects().get(input.getOffset());
        MgObject memberObject = classObject.getObjects().get(variable.getOffset());
        functionObject.getObjects().set(memberObject, output.getOffset());
    }
}
