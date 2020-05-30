package cz.mg.language.entities.mg.logical.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.components.MgComponentL;


public class MgLocationL extends MgArchitectureEntityL implements Named {
    @Part
    private final List<MgLocationL> locations = new List<>();

    @Part
    private final List<MgComponentL> components = new List<>();

    public MgLocationL(ReadableText name) {
        super(name);
    }

    public List<MgLocationL> getLocations() {
        return locations;
    }

    public List<MgComponentL> getComponents() {
        return components;
    }
}