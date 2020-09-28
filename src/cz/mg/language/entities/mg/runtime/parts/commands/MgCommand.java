package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.parts.MgPart;
import cz.mg.language.entities.mg.runtime.roles.MgRunnable;


public abstract class MgCommand implements MgPart, MgRunnable {
    @Mandatory @Link
    private final List<MgFunctionVariable> declaredVariables = new List<>();

    public MgCommand() {
    }

    public List<MgFunctionVariable> getDeclaredVariables() {
        return declaredVariables;
    }
}
