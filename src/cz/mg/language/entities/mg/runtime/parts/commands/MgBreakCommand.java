package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.commands.exceptions.BreakException;


public class MgBreakCommand extends MgCommand {
    @Optional @Link
    private final MgCommand target;

    public MgBreakCommand(MgCommand target) {
        this.target = target;
    }

    public MgCommand getTarget() {
        return target;
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        throw new BreakException(target);
    }
}
