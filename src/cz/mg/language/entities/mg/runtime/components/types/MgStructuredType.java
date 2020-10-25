package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.Clump;
import cz.mg.collections.special.VirtualCollection;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgTypeVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;


public abstract class MgStructuredType extends MgType {
    public MgStructuredType(ReadableText name) {
        super(name);
    }

    public abstract Clump<? extends MgVariable> getVariables();

    public Clump<MgGlobalVariable> getGlobalVariables(){
        return new VirtualCollection<>(getVariables(), MgGlobalVariable.class);
    }

    public Clump<MgTypeVariable> getTypeVariables(){
        return new VirtualCollection<>(getVariables(), MgTypeVariable.class);
    }

    public Clump<MgInstanceVariable> getInstanceVariables(){
        return new VirtualCollection<>(getVariables(), MgInstanceVariable.class);
    }
}
