package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public abstract class MgComponent extends MgObject implements Named {
    @Value
    private final ReadableText name;

    @Link
    private ReadableArray<MgStamp> stamps;

    public MgComponent(MgType type, ReadableText name) {
        super(type);
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public ReadableArray<MgStamp> getStamps() {
        return stamps;
    }

    public void setStamps(ReadableArray<MgStamp> stamps) {
        this.stamps = stamps;
    }
}
