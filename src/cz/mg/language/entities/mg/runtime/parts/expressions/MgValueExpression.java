package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.MgObject;


public class MgValueExpression extends MgExpression {
    @Mandatory @Part
    private final MgAtom object;

    @Mandatory @Link
    private final MgFunctionVariable output;

    public MgValueExpression(MgAtom object, MgFunctionVariable output) {
        this.object = object;
        this.output = output;
    }

    public MgObject getObject() {
        return object;
    }

    public MgFunctionVariable getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        functionInstance.getObjects().set(object.copy(), output.getOffset());
    }
}
