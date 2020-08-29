package cz.mg.language.entities.mg.runtime.atoms;

import cz.mg.language.entities.mg.runtime.objects.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public abstract class MgAtom extends MgObject {
    public MgAtom(MgType type) {
        super(type);
    }

    public abstract MgAtom copy();
}
