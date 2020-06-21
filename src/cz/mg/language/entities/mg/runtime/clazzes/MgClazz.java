package cz.mg.language.entities.mg.runtime.clazzes;

import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.other.MgStamp;


public abstract class MgClazz extends MgObject implements Named {
    @Value
    private final ReadableText name;

    @Link
    private final Array<MgClazz> clazzes;

    @Part
    private final Array<MgVariable> variables;

    @Part
    private final Array<MgFunction> functions;

    @Link
    private final Array<MgStamp> stamps;

    protected MgClazz(MgClazz type, ReadableText name, Array<MgClazz> clazzes, Array<MgVariable> variables, Array<MgFunction> functions, Array<MgStamp> stamps) {
        super(type);
        this.name = name;
        this.clazzes = clazzes;
        this.variables = variables;
        this.functions = functions;
        this.stamps = stamps;
    }

    public MgClazz(ReadableText name, Array<MgClazz> clazzes, Array<MgVariable> variables, Array<MgFunction> functions, Array<MgStamp> stamps) {
        this(Clazzes.CLAZZ, name, clazzes, variables, functions, stamps);
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public Array<MgClazz> getClazzes() {
        return clazzes;
    }

    public Array<MgVariable> getVariables() {
        return variables;
    }

    public Array<MgFunction> getFunctions() {
        return functions;
    }

    public Array<MgStamp> getStamps() {
        return stamps;
    }
}
