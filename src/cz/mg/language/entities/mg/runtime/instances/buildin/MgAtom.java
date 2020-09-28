package cz.mg.language.entities.mg.runtime.instances.buildin;

import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgAtomType;
import cz.mg.language.entities.mg.runtime.instances.MgInstance;
import cz.mg.language.entities.mg.runtime.roles.MgObject;


public abstract class MgAtom implements MgObject, MgInstance {
    @Mandatory @Link
    private final MgAtomType type;

    public MgAtom(MgAtomType type) {
        this.type = type;
    }

    @Override
    public MgAtomType getType() {
        return type;
    }

    public abstract MgAtom copy();
}
