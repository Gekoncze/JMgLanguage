package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;


public class MgLocation extends MgComponent {
    @Part
    private final List<MgComponent> components = new List<>();

    public MgLocation(ReadableText name) {
        super(name);
    }

    public List<MgComponent> getComponents() {
        return components;
    }
}
