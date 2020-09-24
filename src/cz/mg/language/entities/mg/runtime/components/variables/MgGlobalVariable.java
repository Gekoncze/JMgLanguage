package cz.mg.language.entities.mg.runtime.components.variables;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class MgGlobalVariable extends MgVariable {
    private static final MgType TYPE = new MgType("GlobalVariable");

    @Part
    private MgObject object;

    protected MgGlobalVariable(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgGlobalVariable(ReadableText name) {
        super(TYPE, name);
    }

    public MgGlobalVariable(ReadableText name, MgDatatype datatype, MgObject object) {
        super(name, datatype);
        this.object = object;
    }

    public MgObject getObject() {
        return object;
    }

    public void setObject(MgObject object) {
        this.object = object;
    }
}
