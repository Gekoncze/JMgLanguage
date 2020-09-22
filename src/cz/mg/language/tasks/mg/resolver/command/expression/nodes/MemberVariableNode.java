package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;


public class MemberVariableNode extends MemberNode {
    @Mandatory @Link
    private final MgMemberVariable variable;

    public MemberVariableNode(MgMemberVariable variable) {
        super(VariableNode.createInputInterface(variable), VariableNode.createOutputInterface(variable));
        this.variable = variable;
    }

    public MgMemberVariable getVariable() {
        return variable;
    }
}
