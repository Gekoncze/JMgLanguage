package cz.mg.language.entities.mg.runtime.parts.commands.exceptions;

import cz.mg.annotations.storage.Link;
import cz.mg.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;


public class ContinueException extends ArtificialException {
    @Optional @Link
    private final MgCommand target;

    public ContinueException(MgCommand target) {
        this.target = target;
    }

    public MgCommand getTarget() {
        return target;
    }
}
