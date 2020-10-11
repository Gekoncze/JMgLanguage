package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public abstract class AssignmentNode extends OperatorNode {
    public AssignmentNode(@Mandatory List<? extends MgVariable> variables) {
        super(createInputInterface(variables), new OutputInterface(new Array<>()));
    }

    private static InputInterface createInputInterface(@Mandatory List<? extends MgVariable> variables){
        Array<InputConnector> connectors = new Array<>(variables.count());
        int i = 0;
        for(MgVariable variable : variables){
            connectors.set(new InputConnector(variable.getDatatype()), i);
            i++;
        }
        return new InputInterface(connectors);
    }
}
