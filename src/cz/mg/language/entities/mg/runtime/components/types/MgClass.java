package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;


public class MgClass extends MgType {
    private static final MgType TYPE = new MgType("Class");

    @Optional @Link
    private MgClass baseClass;

    @Mandatory @Part
    private ArrayList<MgGlobalVariable> globalVariables;

    @Mandatory @Part
    private ArrayList<MgMemberVariable> variables;

    @Mandatory @Part
    private ArrayList<MgFunction> functions;

    protected MgClass(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgClass(ReadableText name) {
        super(TYPE, name);
    }

    public MgClass getBaseClass() {
        return baseClass;
    }

    public ArrayList<MgGlobalVariable> getGlobalVariables() {
        return globalVariables;
    }

    public ArrayList<MgMemberVariable> getVariables() {
        return variables;
    }

    public ArrayList<MgFunction> getFunctions() {
        return functions;
    }

    public void setBaseClass(MgClass baseClass) {
        this.baseClass = baseClass;
    }

    public void updateCache(){
        int i = 0;
        for(MgMemberVariable variable : getVariables()){
            variable.setOffset(i);
            i++;
        }
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
