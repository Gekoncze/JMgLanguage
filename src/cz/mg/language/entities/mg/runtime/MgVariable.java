package cz.mg.language.entities.mg.runtime;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.roles.MgNamedObject;


public abstract class MgVariable implements MgNamedObject {
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
