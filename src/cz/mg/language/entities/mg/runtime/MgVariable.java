package cz.mg.language.entities.mg.runtime;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.MgComponent;
import cz.mg.language.entities.mg.runtime.components.MgStamp;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public abstract class MgVariable implements MgComponent {
    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Value
    private MgDatatype datatype;

    public MgVariable(ReadableText name) {
        this.name = name;
    }

    public MgVariable(ReadableText name, MgDatatype datatype) {
        this.name = name;
        this.datatype = datatype;
    }

    @Override
    public ArrayList<MgStamp> getStamps() {
        return stamps;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgDatatype getDatatype() {
        return datatype;
    }

    public void setDatatype(MgDatatype datatype) {
        this.datatype = datatype;
    }
}
