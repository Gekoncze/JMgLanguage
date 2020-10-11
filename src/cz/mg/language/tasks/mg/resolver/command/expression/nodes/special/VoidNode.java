package cz.mg.language.tasks.mg.resolver.command.expression.nodes.special;

import cz.mg.collections.array.Array;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.nodes.Node;


public class VoidNode extends Node {
    public VoidNode(OutputInterface childOutputInterface) {
        super(createInputInterface(childOutputInterface), createOutputInterface());
    }

    private static InputInterface createInputInterface(OutputInterface childOutputInterface){
        Array<InputConnector> connectors = new Array<>(childOutputInterface.getConnectors().count());
        int i = 0;
        for(OutputConnector outputConnector : childOutputInterface.getConnectors()){
            connectors.set(new InputConnector(outputConnector.getRequestedDatatype()), i);
            i++;
        }
        return new InputInterface(connectors);
    }

    private static OutputInterface createOutputInterface(){
        return new OutputInterface(new Array<>());
    }
}
