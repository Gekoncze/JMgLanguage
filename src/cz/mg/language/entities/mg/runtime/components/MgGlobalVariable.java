package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


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

    public MgObject getObject() {
        return object;
    }

    public void setObject(MgObject object) {
        this.object = object;
    }
}
