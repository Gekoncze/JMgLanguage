package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public abstract class UnaryOperatorNode extends OperatorNode {
    @Mandatory @Link
    private final List<MgFunction> functions;

    public UnaryOperatorNode(List<MgFunction> functions) {
        super(createInputInterface(functions), createOutputInterface(functions));
        this.functions = functions;
    }

    public List<MgFunction> getFunctions() {
        return functions;
    }

    private static InputInterface createInputInterface(@Mandatory List<MgFunction> functions){
        Array<InputConnector> connectors = new Array<>(functions.count());
        int i = 0;
        for(MgFunction function : functions){
            connectors.set(new InputConnector(function.getInput().getFirst().getDatatype()), i);
            i++;
        }
        return new InputInterface(connectors);
    }

    private static OutputInterface createOutputInterface(@Mandatory List<MgFunction> functions){
        Array<OutputConnector> connectors = new Array<>(functions.count());
        int i = 0;
        for(MgFunction function : functions){
            connectors.set(new OutputConnector(function.getOutput().getFirst().getDatatype()), i);
            i++;
        }
        return new OutputInterface(connectors);
    }
}
