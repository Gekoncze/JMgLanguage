package cz.mg.language.entities.mg.runtime.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.other.MgStamp;
import cz.mg.language.entities.mg.runtime.other.MgVariable;


public class MgClass extends MgType implements Named {
    private static final MgType TYPE = new MgType("Class");

    @Link
    private final ReadableArray<MgClass> clazzes;

    @Part
    private final ReadableArray<MgVariable> variables;

    @Part
    private final ReadableArray<MgFunction> functions;

    protected MgClass(MgType type, ReadableText name, ReadableArray<MgClass> clazzes, ReadableArray<MgVariable> variables, ReadableArray<MgFunction> functions, ReadableArray<MgStamp> stamps) {
        super(type, name, stamps);
        this.clazzes = clazzes;
        this.variables = variables;
        this.functions = functions;
    }

    public MgClass(ReadableText name, ReadableArray<MgClass> clazzes, ReadableArray<MgVariable> variables, ReadableArray<MgFunction> functions, ReadableArray<MgStamp> stamps) {
        this(TYPE, name, clazzes, variables, functions, stamps);
    }

    public ReadableArray<MgClass> getClazzes() {
        return clazzes;
    }

    public ReadableArray<MgVariable> getVariables() {
        return variables;
    }

    public ReadableArray<MgFunction> getFunctions() {
        return functions;
    }
}
