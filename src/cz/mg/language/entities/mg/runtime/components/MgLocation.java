package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgLocation extends MgComponent {
    private static final MgType TYPE = new MgType("Location");

    @Part
    private final List<MgComponent> components = new List<>();

    protected MgLocation(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgLocation(ReadableText name) {
        super(TYPE, name);
    }

    public List<MgComponent> getComponents() {
        return components;
    }
}
