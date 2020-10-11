package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class AssignmentNode extends OperatorNode {
    public AssignmentNode(InputInterface voidInputInterface) {
        super(createInputInterface(voidInputInterface), new OutputInterface(new Array<>()));
    }

    private static InputInterface createInputInterface(InputInterface voidInputInterface){
        Array<InputConnector> connectors = new Array<>(voidInputInterface.getConnectors().count());
        int i = 0;
        for(InputConnector voidInputConnector : voidInputInterface.getConnectors()){
            connectors.set(new InputConnector(voidInputConnector.getRequestedDatatype()), i);
            i++;
        }
        return new InputInterface(connectors);
    }
}
