package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.buildin.types.MgObjectType;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;
import cz.mg.language.entities.mg.runtime.roles.MgType;
import cz.mg.language.entities.mg.runtime.parts.MgMemberVariable;


public class MgClass implements MgComponent, MgType {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    @Optional @Link
    private MgClass baseClass;

    @Mandatory @Part
    private final ArrayList<MgGlobalVariable> globalVariables = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgMemberVariable> variables = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgFunction> functions = new ArrayList<>();

    @Optional @Cache
    private Integer variableCountCache;

    public MgClass(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    @Override
    public ArrayList<MgStamp> getStamps() {
        return stamps;
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

    public Integer getVariableCountCache() {
        if(variableCountCache == null) updateVariableCountCache();
        return variableCountCache;
    }

    public void updateVariableOffsetCache(){
        int i = 0;
        for(MgMemberVariable variable : getVariables()){
            variable.setOffset(i);
            i++;
        }
    }

    private void updateVariableCountCache(){
        int count = 0;
        MgClass clazz = this;
        while(clazz != null){
            count += clazz.getVariables().count();
            clazz = clazz.getBaseClass();
        }
        variableCountCache = count;
    }

    @Override
    public boolean is(MgType baseType) {
        if(baseType == MgObjectType.getInstance()) return true;
        if(baseType == this) return true;
        if(baseClass != null) return baseClass.is(baseType);
        return false;
    }
}
