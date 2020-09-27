package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.MgPart;
import cz.mg.language.entities.mg.runtime.roles.MgRunnable;


public abstract class MgCommand implements MgPart, MgRunnable {
    @Mandatory @Link
    private final List<MgLocalVariable> declaredVariables = new List<>();

    public MgCommand() {
    }

    public List<MgLocalVariable> getDeclaredVariables() {
        return declaredVariables;
    }
}
