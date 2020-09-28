package cz.mg.language.entities.mg.runtime.components.types.buildin;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;


public abstract class MgAtomType extends MgType {
    public MgAtomType(ReadableText name) {
        super(name);
    }

    @Override
    public boolean is(MgType baseType) {
        if(baseType == MgObjectType.getInstance()) return true;
        if(baseType == this) return true;
        return false;
    }

    public abstract @Mandatory MgAtom create(@Mandatory ReadableText text);
}
