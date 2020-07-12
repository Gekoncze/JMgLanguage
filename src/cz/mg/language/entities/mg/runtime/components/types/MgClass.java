package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.MgVariable;


public class MgClass extends MgType implements Named {
    private static final MgType TYPE = new MgType("Class");

    @Link
    private ReadableArray<MgClass> classes;

    @Part
    private ReadableArray<MgVariable> variables;

    @Part
    private ReadableArray<MgFunction> functions;

    protected MgClass(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgClass(ReadableText name) {
        super(TYPE, name);
    }

    public ReadableArray<MgClass> getClasses() {
        return classes;
    }

    public ReadableArray<MgVariable> getVariables() {
        return variables;
    }

    public ReadableArray<MgFunction> getFunctions() {
        return functions;
    }

    public void setClasses(ReadableArray<MgClass> classes) {
        this.classes = classes;
    }

    public void setVariables(ReadableArray<MgVariable> variables) {
        this.variables = variables;
    }

    public void setFunctions(ReadableArray<MgFunction> functions) {
        this.functions = functions;
    }
}
