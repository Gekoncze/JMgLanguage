package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.MgVariable;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class VariableNode extends Node {
    public VariableNode(MgVariable variable) {
        super(createInputInterface(variable), createOutputInterface(variable));
    }

    public static InputInterface createInputInterface(@Mandatory MgVariable variable){
        return new InputInterface(new Array<>()); // no input for variables
    }

    public static OutputInterface createOutputInterface(@Mandatory MgVariable variable){
        return new OutputInterface(new Array<>(new OutputConnector(variable.getDatatype())));
    }
}
