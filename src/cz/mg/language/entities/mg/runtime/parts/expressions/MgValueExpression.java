package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.buildin.atoms.MgAtom;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.roles.MgObject;


public class MgValueExpression extends MgExpression {
    @Mandatory @Part
    private final MgAtom object;

    @Mandatory @Link
    private final MgLocalVariable output;

    public MgValueExpression(MgAtom object, MgLocalVariable output) {
        this.object = object;
        this.output = output;
    }

    public MgObject getObject() {
        return object;
    }

    public MgLocalVariable getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        functionObject.getObjects().set(object.copy(), output.getOffset());
    }
}
