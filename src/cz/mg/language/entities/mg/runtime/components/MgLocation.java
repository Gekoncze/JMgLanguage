package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgLocation extends MgComponent {
    private static final MgType TYPE = new MgType("Location");

    @Part
    private final List<MgObject> objects = new List<>();

    protected MgLocation(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgLocation(ReadableText name) {
        super(TYPE, name);
    }

    public List<MgObject> getObjects() {
        return objects;
    }
}
