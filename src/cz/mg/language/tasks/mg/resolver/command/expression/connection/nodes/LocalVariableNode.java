package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgLocalVariableExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class LocalVariableNode extends Node {
    @Mandatory @Link
    private final MgLocalVariable variable;

    public LocalVariableNode(MgLocalVariable variable) {
        super(createInputInterface(variable), createOutputInterface(variable));
        this.variable = variable;
    }

    @Override
    public MgExpression createExpression() {
        return new MgLocalVariableExpression(variable);
    }

    private static InputInterface createInputInterface(@Mandatory MgLocalVariable variable){
        return new InputInterface(new Array<>()); // no input for variables
    }

    private static OutputInterface createOutputInterface(@Mandatory MgLocalVariable variable){
        return new OutputInterface(new Array<>(new OutputConnector(variable.getDatatype())));
    }
}
