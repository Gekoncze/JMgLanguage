package cz.mg.language.entities.logic.mg.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public abstract class MgBlockCommandL extends MgCommandL {
    @Part
    private final List<MgCommandL> commands = new List<>();

    public List<MgCommandL> getCommands() {
        return commands;
    }
}
