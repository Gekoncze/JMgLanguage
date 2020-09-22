package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public abstract class MemberNode extends Node {
    public MemberNode(InputInterface inputInterface, OutputInterface outputInterface) {
        super(inputInterface, outputInterface);
    }
}
