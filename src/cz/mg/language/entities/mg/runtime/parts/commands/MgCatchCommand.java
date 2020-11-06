package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public class MgCatchCommand extends MgBlockCommand {
    @Mandatory @Link
    private final MgInstanceVariable input;

    public MgCatchCommand(@Link MgInstanceVariable input) {
        this.input = input;
    }

    public MgInstanceVariable getInput() {
        return input;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        super.run(functionInstance);
    }
}
