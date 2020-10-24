package cz.mg.language.entities.mg.runtime.components.types.classes;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgObjectType;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgClassVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;


public class MgClass extends MgStructuredType {
    @Optional @Link
    private MgClass baseClass;

    @Mandatory @Part
    private final ArrayList<MgVariable> variables = new ArrayList<>();

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

    public ArrayList<MgVariable> getVariables() {
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
        for(MgVariable variable : getVariables()){
            if(variable instanceof MgClassVariable){
                ((MgClassVariable) variable).setOffset(i);
                i++;
            }
        }
        return i;
    }

    private void updateVariableCountCache(){
        int count = 0;
        MgClass clazz = this;
        while(clazz != null){
            for(MgVariable variable : getVariables()){
                if(variable instanceof MgClassVariable){
                    count++;
                }
            }
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
