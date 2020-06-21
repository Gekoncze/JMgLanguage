package cz.mg.language.entities.mg.runtime.clazzes;

import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.other.MgDatatype;
import cz.mg.language.entities.mg.runtime.other.MgStamp;


public class MgVariable extends MgClazz implements Named {
    @Value
    private final MgDatatype datatype;

    protected MgVariable(MgClazz type, ReadableText name, Array<MgClazz> clazzes, Array<MgVariable> variables, Array<MgFunction> functions, Array<MgStamp> stamps, MgDatatype datatype) {
        super(type, name, clazzes, variables, functions, stamps);
        this.datatype = datatype;
    }

    public MgVariable(ReadableText name, MgDatatype datatype, Array<MgStamp> stamps) {
        super(Clazzes.VARIABLE, name, new Array<>(0), new Array<>(0), new Array<>(0), stamps);
        this.datatype = datatype;
    }

    public MgDatatype getDatatype() {
        return datatype;
    }
}
