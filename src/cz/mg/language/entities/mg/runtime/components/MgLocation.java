package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;


public class MgLocation implements MgComponent {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    @Part
    private final List<MgComponent> components = new List<>();

    public MgLocation(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    @Override
    public ArrayList<MgStamp> getStamps() {
        return stamps;
    }

    public List<MgComponent> getComponents() {
        return components;
    }
}
