package cz.mg.language.entities.mg.logical.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;


public class MgLogicalLocation extends MgLogicalArchitectureEntity implements Named {
    @Part
    private final List<MgLogicalLocation> locations = new List<>();

    @Part
    private final List<MgLogicalComponent> components = new List<>();

    public MgLogicalLocation() {
    }

    public MgLogicalLocation(ReadableText name) {
        super(name);
    }

    public List<MgLogicalLocation> getLocations() {
        return locations;
    }

    public List<MgLogicalComponent> getComponents() {
        return components;
    }
}