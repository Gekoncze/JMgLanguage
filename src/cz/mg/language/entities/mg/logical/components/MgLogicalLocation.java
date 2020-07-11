package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;


public class MgLogicalLocation extends MgLogicalComponent {
    @Part
    private final List<MgLogicalComponent> components = new List<>();

    public MgLogicalLocation() {
    }

    public MgLogicalLocation(ReadableText name) {
        super(name);
    }

    public List<MgLogicalComponent> getComponents() {
        return components;
    }
}