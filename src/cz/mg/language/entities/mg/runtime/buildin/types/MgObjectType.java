package cz.mg.language.entities.mg.runtime.buildin.types;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.entities.mg.runtime.components.MgType;


public class MgObjectType implements MgType {
    private static final MgObjectType INSTANCE = new MgObjectType();

    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    public static MgObjectType getInstance() {
        return INSTANCE;
    }

    private MgObjectType() {
        this.name = new ReadonlyText("Object");
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    @Override
    public ArrayList<MgStamp> getStamps() {
        return stamps;
    }

    @Override
    public boolean is(MgType baseType) {
        return false;
    }
}
