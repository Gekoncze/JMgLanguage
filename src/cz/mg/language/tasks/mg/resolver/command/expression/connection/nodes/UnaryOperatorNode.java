package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgUnaryOperatorExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.*;

import java.util.Iterator;


public class UnaryOperatorNode extends OperatorNode {
    @Mandatory @Link
    private final List<MgFunction> functions;

    public UnaryOperatorNode(List<MgFunction> functions) {
        super(createInputInterface(functions), createOutputInterface(functions));
        this.functions = functions;
    }

    @Override
    public MgExpression createExpression() {
        List<MgUnaryOperatorExpression.Replication> replications = new List<>();

        Array<Integer> input = gatherInputOffset();
        Array<Integer> output = gatherOutputOffset();

        int i = 0;
        for(MgFunction function : functions){
            replications.addLast(
                new MgUnaryOperatorExpression.Replication(
                    function,
                    input.get(i),
                    output.get(i)
                )
            );
            i++;
        }

        if(getChildren().count() != 1) throw new RuntimeException();
        return new MgUnaryOperatorExpression(
            getChildren().getFirst().createExpression(),
            replications
        );
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

    private Array<Integer> gatherInputOffset(){
        Array<Integer> offset = new Array<>(functions.count());
        Iterator<InputConnector> iterator = getInputInterface().getConnectors().iterator();
        for(int i = 0; i < functions.count(); i++){
            offset.set(i, iterator.next().getConnection().getConnectionVariable().getOffset());
        }
        return offset;
    }

    private Array<Integer> gatherOutputOffset(){
        Array<Integer> offset = new Array<>(functions.count());
        Iterator<OutputConnector> iterator = getOutputInterface().getConnectors().iterator();
        for(int i = 0; i < functions.count(); i++){
            offset.set(i, iterator.next().getConnection().getConnectionVariable().getOffset());
        }
        return offset;
    }
}
