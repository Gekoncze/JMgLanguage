package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgObject;


public class MgGlobalVariable extends MgVariable {
    @Optional @Part
    private MgObject object;

    public MgGlobalVariable(ReadableText name) {
        super(name);
    }

    public MgObject getObject() {
        return object;
    }

    public void setObject(MgObject object) {
        this.object = object;
    }
}
