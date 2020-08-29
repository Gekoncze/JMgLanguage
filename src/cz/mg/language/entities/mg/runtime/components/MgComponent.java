package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.CollectionPart;
import cz.mg.language.annotations.entity.ItemsLink;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.CollectionMandatory;
import cz.mg.language.annotations.requirement.ItemsMandatory;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public abstract class MgComponent extends MgObject implements Named {
    @Mandatory @Value
    private final ReadableText name;

    @CollectionMandatory @CollectionPart
    @ItemsMandatory @ItemsLink
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
