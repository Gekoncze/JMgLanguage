package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.*;


public class FunctionNode extends Node {
    @Mandatory
    @Link
    private final MgFunction function;

    public FunctionNode(MgFunction function) {
        super(createInputInterface(function), createOutputInterface(function));
        this.function = function;
    }

    public MgFunction getFunction() {
        return function;
    }

    private static InputInterface createInputInterface(@Mandatory MgFunction function){
        Array<InputConnector> connectors = new Array<>(function.getInput().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new InputConnector(function.getInput().get(i).getDatatype()), i);
        }
        return new InputInterface(connectors);
    }

    private static OutputInterface createOutputInterface(@Mandatory MgFunction function){
        if(function == null) throw new RuntimeException();
        Array<OutputConnector> connectors = new Array<>(function.getOutput().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new OutputConnector(function.getOutput().get(i).getDatatype()), i);
        }
        return new OutputInterface(connectors);
    }
}