package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public abstract class OperatorNode extends Node {
    public OperatorNode(InputInterface inputInterface, OutputInterface outputInterface) {
        super(inputInterface, outputInterface);
    }
}
