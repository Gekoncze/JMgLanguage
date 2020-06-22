package cz.mg.language.entities.mg.runtime.types;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.Named;
import cz.mg.language.entities.mg.runtime.other.MgStamp;


public class MgType extends MgObject implements Named {
    @Value
    private final ReadableText name;

    @Link
    private final ReadableArray<MgStamp> stamps;

    public MgType(String name) {
        this(null, new ReadonlyText(name), new Array<>());
    }

    public MgType(MgType type, ReadableText name, ReadableArray<MgStamp> stamps) {
        super(type);
        this.name = name;
        this.stamps = stamps;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public ReadableArray<MgStamp> getStamps() {
        return stamps;
    }
}
