package cz.mg.language.entities.mg.runtime.parts.commands.exceptions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class RollbackException extends ArtificialException {
    @Mandatory @Part
    private final MgObject object;

    public RollbackException(MgObject object) {
        this.object = object;
    }

    public MgObject getObject() {
        return object;
    }
}
