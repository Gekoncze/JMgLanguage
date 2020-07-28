package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.components.MgVariable;


class Condition {
    @Link
    private final MgVariable variable;

    @Link
    private final Command command;

    public Condition(MgVariable variable, Command command) {
        this.variable = variable;
        this.command = command;
    }

    public MgVariable getVariable() {
        return variable;
    }

    public Command getCommand() {
        return command;
    }
}