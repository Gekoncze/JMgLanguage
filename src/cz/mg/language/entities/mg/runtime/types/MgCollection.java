package cz.mg.language.entities.mg.runtime.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.other.MgParameter;
import cz.mg.language.entities.mg.runtime.other.MgStamp;
import cz.mg.language.entities.mg.runtime.other.MgVariable;


public class MgCollection extends MgClass {
    private static final MgType TYPE = new MgType("Collection");

    @Part
    private final ReadableArray<MgParameter> parameters;

    protected MgCollection(MgType type, ReadableText name, ReadableArray<MgClass> clazzes, ReadableArray<MgVariable> variables, ReadableArray<MgFunction> functions, ReadableArray<MgParameter> parameters, ReadableArray<MgStamp> stamps) {
        super(type, name, clazzes, variables, functions, stamps);
        this.parameters = parameters;
    }

    public MgCollection(ReadableText name, ReadableArray<MgClass> clazzes, ReadableArray<MgVariable> variables, ReadableArray<MgFunction> functions, ReadableArray<MgParameter> parameters, ReadableArray<MgStamp> stamps) {
        this(TYPE, name, clazzes, variables, functions, parameters, stamps);
    }

    public ReadableArray<MgParameter> getParameters() {
        return parameters;
    }
}
