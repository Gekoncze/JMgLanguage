package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgLogicalValueExpression extends MgExpression {
    @Mandatory @Part
    private final MgObject object;

    public MgLogicalValueExpression(MgObject object) {
        this.object = object;
    }

    public MgObject getObject() {
        return object;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
