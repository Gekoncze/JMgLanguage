package cz.mg.language.entities.mg.runtime.components.types.classes;

import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.Clump;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.special.CompositeCollection;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgObjectType;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.*;


public class MgClass extends MgStructuredType {
    @Optional @Link
    private MgClass baseClass;

    @Mandatory @Part
    private final ArrayList<MgVariable> variableDefinitions = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgFunction> functionDefinitions = new ArrayList<>();

    @Optional @Cache
    private MgCache cache;

    public MgClass(ReadableText name) {
        super(name);
    }

    public MgClass getBaseClass() {
        return baseClass;
    }

    public ArrayList<MgVariable> getVariableDefinitions() {
        return variableDefinitions;
    }

    public ArrayList<MgFunction> getFunctionDefinitions() {
        return functionDefinitions;
    }

    public void setBaseClass(MgClass baseClass) {
        this.baseClass = baseClass;
    }

    public MgCache getCache() {
        if(cache == null) cache = new MgCache(this);
        return cache;
    }

    @Override
    public Clump<MgVariable> getVariables() {
        return new CompositeCollection<>(baseClass.getVariables(), variableDefinitions);
    }

    @Override
    public boolean is(MgType baseType) {
        if(baseType == MgObjectType.getInstance()) return true;
        if(baseType == this) return true;
        if(baseClass != null) return baseClass.is(baseType);
        return false;
    }

    public static class MgCache {
        @Mandatory @Value
        private final int variableCount;

        public MgCache(MgClass clazz) {
            this.variableCount = Clump.count(clazz.getInstanceVariables());
        }

        public int getVariableCount() {
            return variableCount;
        }
    }
}
