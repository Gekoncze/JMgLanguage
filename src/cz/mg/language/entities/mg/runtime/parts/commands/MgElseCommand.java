package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgElseCommand extends MgCaseCommand {
    public MgElseCommand(MgExpression expression, List<MgCommand> commands) {
        super(expression, commands);
    }
}
