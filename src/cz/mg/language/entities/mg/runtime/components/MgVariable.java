package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;


public class MgVariable extends MgComponent {
    private static final MgType TYPE = new MgType("Variable");

    @Value
    private final MgDatatype datatype;

    protected MgVariable(MgType type, ReadableText name, MgDatatype datatype) {
        super(type, name);
        this.datatype = datatype;
    }

    public MgVariable(ReadableText name, MgDatatype datatype) {
        super(TYPE, name);
        this.datatype = datatype;
    }

    public MgDatatype getDatatype() {
        return datatype;
    }
}
