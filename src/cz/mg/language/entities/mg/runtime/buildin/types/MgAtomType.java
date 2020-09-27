package cz.mg.language.entities.mg.runtime.buildin.types;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.buildin.atoms.MgAtom;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.entities.mg.runtime.roles.MgObject;
import cz.mg.language.entities.mg.runtime.roles.MgType;


public abstract class MgAtomType implements MgObject, MgType {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    public MgAtomType(ReadableText name) {
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

    @Override
    public boolean is(MgType baseType) {
        if(baseType == MgObjectType.getInstance()) return true;
        if(this == baseType) return true;
        return false;
    }

    public abstract @Mandatory MgAtom create(@Mandatory ReadableText text);
}
