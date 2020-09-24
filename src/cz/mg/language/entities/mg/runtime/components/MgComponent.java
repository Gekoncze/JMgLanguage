package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public abstract class MgComponent extends MgObject implements Named {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    public MgComponent(MgType type, ReadableText name) {
        super(type);
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
