package cz.mg.language.entities.mg.logical.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalClumpExpression;


public class MgLogicalSwitchCommand extends MgLogicalCommand {
    @Optional @Part
    private MgLogicalClumpExpression expression;

    @Mandatory @Part
    private final List<MgLogicalCaseCommand> commands = new List<>();

    public MgLogicalSwitchCommand() {
    }

    public MgLogicalSwitchCommand(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }

    public MgLogicalClumpExpression getExpression() {
        return expression;
    }

    public void setExpression(MgLogicalClumpExpression expression) {
        this.expression = expression;
    }

    public List<MgLogicalCaseCommand> getCommands() {
        return commands;
    }
}
