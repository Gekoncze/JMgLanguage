package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgClassVariable;


public class MemberVariableNode extends Node {
    @Mandatory @Link
    private final MgClassVariable variable;

    public MemberVariableNode(MgClassVariable variable) {
        super(VariableNode.createInputInterface(variable), VariableNode.createOutputInterface(variable));
        this.variable = variable;
    }

    public MgClassVariable getVariable() {
        return variable;
    }
}
