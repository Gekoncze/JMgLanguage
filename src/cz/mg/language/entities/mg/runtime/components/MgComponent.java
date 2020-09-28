package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.MgNamedObject;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;


public class MgComponent implements MgNamedObject {
    @Mandatory
    @Value
    private final ReadableText name;

    @Mandatory @Part
    private final ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    public MgComponent(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public ArrayList<MgStamp> getStamps() {
        return stamps;
    }
}
