package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgCaseCommand extends MgCommand {
    @Optional @Part
    private final MgExpression expression;

    @Optional @Value
    private final MgLocalVariable input;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgCaseCommand(MgExpression expression, MgLocalVariable input, List<MgCommand> commands) {
        this.expression = expression;
        this.input = input;
        this.commands = commands;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public MgLocalVariable getInput() {
        return input;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        // expression should be handled by switch command
        for(MgCommand command : commands){
            command.run(functionObject);
        }
    }
}
