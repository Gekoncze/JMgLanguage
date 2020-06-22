package cz.mg.language.entities.mg.runtime.other;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;


public class MgVariable extends MgOther implements Named {
    @Value
    private final ReadableText name;

    @Value
    private final MgDatatype datatype;

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
}
