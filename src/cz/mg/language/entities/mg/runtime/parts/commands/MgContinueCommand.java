package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.ContinueException;


public class MgContinueCommand extends MgCommand {
    @Optional @Link
    private final MgCommand target;

    public MgContinueCommand(MgCommand target) {
        this.target = target;
    }

    public MgCommand getTarget() {
        return target;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        throw new ContinueException(target);
    }
}
