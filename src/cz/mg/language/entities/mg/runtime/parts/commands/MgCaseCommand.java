package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgCaseCommand extends MgCommand {
    @Optional @Part
    private final MgExpression expression;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgCaseCommand(MgExpression expression, List<MgCommand> commands) {
        this.expression = expression;
        this.commands = commands;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
