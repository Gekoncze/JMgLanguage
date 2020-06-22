package cz.mg.language.entities.mg.runtime.architecture;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.types.MgType;


public class MgLocation extends MgArchitectureObject {
    private static final MgType TYPE = new MgType(null, new ReadonlyText("Location"), new Array()) {};

    @Part
    private final List<MgObject> objects = new List<>();

    public MgLocation(ReadableText name) {
        super(TYPE, name);
    }

    public List<MgObject> getObjects() {
        return objects;
    }
}
