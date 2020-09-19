package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgGroupExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.*;


public class GroupNode extends Node {
    public GroupNode(InputInterface parentInputInterface) {
        super(copyAsInput(parentInputInterface), copyAsOutput(parentInputInterface));
    }

    public GroupNode(OutputInterface childOutputInterface) {
        super(copyAsInput(childOutputInterface), copyAsOutput(childOutputInterface));
    }

    private static InputInterface copyAsInput(Interface<? extends Connector> inputInterface){
        Array<InputConnector> connectors = new Array<>(inputInterface.getConnectors().count());
        for(int i = 0; i < inputInterface.getConnectors().count(); i++){
            connectors.set(new InputConnector(inputInterface.getConnectors().get(i).getRequestedDatatype()), i);
        }
        return new InputInterface(connectors);
    }

    private static OutputInterface copyAsOutput(Interface<? extends Connector> outputInterface){
        Array<OutputConnector> connectors = new Array<>(outputInterface.getConnectors().count());
        for(int i = 0; i < outputInterface.getConnectors().count(); i++){
            connectors.set(new OutputConnector(outputInterface.getConnectors().get(i).getRequestedDatatype()), i);
        }
        return new OutputInterface(connectors);
    }

    @Override
    public MgExpression createExpression() {
        return new MgGroupExpression(createExpressions());
    }

    private List<MgExpression> createExpressions(){
        List<MgExpression> expressions = new List<>();
        for(Node in : getChildren()){
            expressions.addLast(in.createExpression());
        }
        return expressions;
    }
}
