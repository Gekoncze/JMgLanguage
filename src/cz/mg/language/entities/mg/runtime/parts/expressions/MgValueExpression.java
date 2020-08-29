package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.atoms.MgAtom;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgValueExpression extends MgExpression {
    @Mandatory @Part
    private final MgAtom object;

    @Mandatory @Value
    private final int output;

    public MgValueExpression(MgAtom object, int output) {
        this.object = object;
        this.output = output;
    }

    public MgObject getObject() {
        return object;
    }

    public int getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        functionObject.getObjects().set(object.copy(), output);
    }
}
