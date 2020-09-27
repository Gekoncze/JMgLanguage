package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.roles.MgObject;


public class MgGlobalVariable extends MgVariable implements MgComponent {
    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    @Part
    private MgObject object;

    public MgGlobalVariable(ReadableText name) {
        super(name);
    }

    public MgGlobalVariable(ReadableText name, MgDatatype datatype) {
        super(name, datatype);
    }

    @Override
    public ArrayList<MgStamp> getStamps() {
        return stamps;
    }

    public MgObject getObject() {
        return object;
    }

    public void setObject(MgObject object) {
        this.object = object;
    }
}
