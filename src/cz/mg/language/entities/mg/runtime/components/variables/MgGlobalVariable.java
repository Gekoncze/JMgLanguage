package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.MgObject;


public class MgGlobalVariable extends MgVariable {
    @Part
    private MgObject object;

    public MgGlobalVariable(ReadableText name) {
        super(name);
    }

    public MgGlobalVariable(ReadableText name, MgDatatype datatype) {
        super(name, datatype);
    }

    public MgObject getObject() {
        return object;
    }

    public void setObject(MgObject object) {
        this.object = object;
    }
}
