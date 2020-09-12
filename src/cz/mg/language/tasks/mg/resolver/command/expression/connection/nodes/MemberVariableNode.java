package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgMemberVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgMemberVariableExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class MemberVariableNode extends Node {
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

    private static InputInterface createInputInterface(@Mandatory MgMemberVariable variable){
        return new InputInterface(new Array<>()); // no input for variables
    }

    private static OutputInterface createOutputInterface(@Mandatory MgMemberVariable variable){
        return new OutputInterface(new Array<>(new OutputConnector(variable.getDatatype())));
    }
}
