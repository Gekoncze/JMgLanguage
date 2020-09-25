package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;


public class MgElseCommand extends MgCaseCommand {
    public MgElseCommand(List<MgCommand> commands) {
        super(null, null, commands);
    }
}
