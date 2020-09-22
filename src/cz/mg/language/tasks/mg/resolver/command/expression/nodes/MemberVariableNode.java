package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.member.MgMemberVariableExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;


public class MemberVariableNode extends MemberNode {
    @Mandatory @Link
    private final MgMemberVariable variable;

    public MemberVariableNode(MgMemberVariable variable) {
        super(createInputInterface(variable), createOutputInterface(variable));
        this.variable = variable;
    }

    @Override
    public MgExpression createExpression() {
        return new MgMemberVariableExpression(
            variable,
            getOutputInterface().getConnectors().getFirst().getConnection().getConnectionVariable().getOffset()
        );
    }
}
