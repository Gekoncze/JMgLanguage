package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.MgVariable;


public class MgClass extends MgType {
    private static final MgType TYPE = new MgType("Class");

    @Optional @Link
    private MgClass baseClass;

    @Part
    private ReadableArray<MgVariable> variables;

    @Part
    private ReadableArray<MgFunction> functions;

    @Part
    private ReadableArray<MgGlobalVariable> globalVariables;

    protected MgClass(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgClass(ReadableText name) {
        super(TYPE, name);
    }

    public MgClass getBaseClass() {
        return baseClass;
    }

    public ReadableArray<MgVariable> getVariables() {
        return variables;
    }

    public ReadableArray<MgFunction> getFunctions() {
        return functions;
    }

    public ReadableArray<MgGlobalVariable> getGlobalVariables() {
        return globalVariables;
    }

    public void setBaseClass(MgClass baseClass) {
        this.baseClass = baseClass;
    }

    public void setVariables(ReadableArray<MgVariable> variables) {
        this.variables = variables;
    }

    public void setFunctions(ReadableArray<MgFunction> functions) {
        this.functions = functions;
    }

    public void setGlobalVariables(ReadableArray<MgGlobalVariable> globalVariables) {
        this.globalVariables = globalVariables;
    }

    @Override
    public boolean is(MgType type) {
        if(type == this) return true;
        if(baseClass != null){
            return baseClass.is(type);
        } else {
            return false;
        }
    }
}
