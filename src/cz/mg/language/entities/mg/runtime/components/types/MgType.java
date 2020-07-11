package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgStamp;


public class MgType extends MgComponent {
    @Link
    private ReadableArray<MgStamp> stamps;

    public MgType(String name) {
        this(null, new ReadonlyText(name));
    }

    public MgType(MgType type, ReadableText name) {
        super(type, name);
    }

    public ReadableArray<MgStamp> getStamps() {
        return stamps;
    }

    public void setStamps(ReadableArray<MgStamp> stamps) {
        this.stamps = stamps;
    }
}
