package cz.mg.language.entities.runtime.mg.architecture;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.MgNamedR;
import cz.mg.language.entities.runtime.mg.components.MgComponentR;


public class MgLocationR extends MgArchitectureEntityR implements MgNamedR {
    @Value
    private final ReadableText name;

    @Part
    private final List<MgLocationR> locations = new List<>();

    @Part
    private final List<MgComponentR> definitions = new List<>();

    public MgLocationR(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<MgLocationR> getLocations() {
        return locations;
    }

    public List<MgComponentR> getDefinitions() {
        return definitions;
    }
}
