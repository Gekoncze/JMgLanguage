package cz.mg.language.entities.mg.runtime.parts.commands.exceptions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.parts.commands.MgCommand;


public class BreakException extends ArtificialException {
    @Optional @Link
    private final MgCommand target;

    public BreakException(MgCommand target) {
        this.target = target;
    }

    public MgCommand getTarget() {
        return target;
    }
}
