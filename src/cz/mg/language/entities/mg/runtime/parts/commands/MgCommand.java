package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.parts.MgPart;


public abstract class MgCommand extends MgPart implements MgRunnable {
    @Mandatory @Part
    private final List<MgVariable> declaredVariables = new List<>();

    public MgCommand() {
    }

    public List<MgVariable> getDeclaredVariables() {
        return declaredVariables;
    }
}
