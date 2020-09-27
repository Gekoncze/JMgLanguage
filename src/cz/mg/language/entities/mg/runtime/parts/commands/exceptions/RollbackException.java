package cz.mg.language.entities.mg.runtime.parts.commands.exceptions;

import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.roles.MgInstance;


public class RollbackException extends ArtificialException {
    @Mandatory @Part
    private final MgInstance object;

    public RollbackException(MgInstance object) {
        this.object = object;
    }

    public MgInstance getObject() {
        return object;
    }
}
