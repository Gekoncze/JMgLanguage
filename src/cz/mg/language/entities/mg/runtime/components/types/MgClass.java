package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgObjectType;
import cz.mg.language.entities.mg.runtime.components.variables.MgClassVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;


public class MgClass extends MgType {
    @Optional @Link
    private MgClass baseClass;

    @Mandatory @Part
    private final ArrayList<MgGlobalVariable> globalVariables = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgClassVariable> variables = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgFunction> functions = new ArrayList<>();

    @Optional @Cache
    private Integer variableCountCache;

    public MgClass(ReadableText name) {
        super(name);
    }

    public MgClass getBaseClass() {
        return baseClass;
    }

    public ArrayList<MgGlobalVariable> getGlobalVariables() {
        return globalVariables;
    }

    public ArrayList<MgClassVariable> getVariables() {
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

    public int updateVariableOffsetCache(){
        int i = 0;
        if(baseClass != null) i = baseClass.updateVariableOffsetCache();
        for(MgClassVariable variable : getVariables()){
            variable.setOffset(i);
            i++;
        }
        return i;
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
