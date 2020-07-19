package cz.mg.language.tasks.mg.resolver.command;

import cz.mg.language.entities.mg.runtime.components.MgVariable;


class Condition {
    private final MgVariable variable;
    private final Node node;

    public Condition(MgVariable variable, Node node) {
        this.variable = variable;
        this.node = node;
    }

    public MgVariable getVariable() {
        return variable;
    }

    public Node getNode() {
        return node;
    }
}