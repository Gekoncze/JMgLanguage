package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;


public class MgUnresolvedLocation extends MgUnresolvedComponent {
    @Part
    private final List<MgUnresolvedComponent> components = new List<>();

    public MgUnresolvedLocation(ReadableText name) {
        super(name);
    }

    public List<MgUnresolvedComponent> getComponents() {
        return components;
    }
}