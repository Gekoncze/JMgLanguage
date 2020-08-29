package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgWhileCommand extends MgCommand implements Breakable, Continuable {
    @Optional @Part
    private final ReadableText name;

    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Part
    private final List<MgCommand> commands;

    public MgWhileCommand(ReadableText name, MgExpression expression, List<MgCommand> commands) {
        this.name = name;
        this.expression = expression;
        this.commands = commands;
    }

    public ReadableText getName() {
        return name;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public List<MgCommand> getCommands() {
        return commands;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
