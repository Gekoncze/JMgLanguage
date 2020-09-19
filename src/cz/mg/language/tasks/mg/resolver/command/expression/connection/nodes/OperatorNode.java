package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public abstract class OperatorNode extends Node {
    public OperatorNode(InputInterface inputInterface, OutputInterface outputInterface) {
        super(inputInterface, outputInterface);
    }

    protected List<MgExpression> createExpressions(){
        List<MgExpression> expressions = new List<>();
        for(Node in : getInput()){
            expressions.addLast(in.createExpression());
        }
        return expressions;
    }
}
