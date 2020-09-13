package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.*;

import java.util.Iterator;


public abstract class OperatorNode extends Node {
    @Mandatory @Link
    protected final MgFunction function;

    @Mandatory @Value
    protected final int replication;

    public OperatorNode(@Mandatory MgFunction function, int replication) {
        super(createInputInterface(function, replication), createOutputInterface(function, replication));
        this.function = function;
        this.replication = replication;
    }

    protected List<MgExpression> createLeftExpressions(){
        List<MgExpression> expressions = new List<>();
        Iterator<Node> inputIterator = getInput().iterator();
        for(int i = 0; i < replication; i++){
            Node in = inputIterator.next();
            expressions.addLast(in.createExpression());
        }
        return expressions;
    }

    protected List<MgExpression> createRightExpressions(){
        List<MgExpression> expressions = new List<>();
        Iterator<Node> inputIterator = getInput().iterator();
        for(int i = 0; i < replication; i++) inputIterator.next();
        for(int i = 0; i < replication; i++){
            Node in = inputIterator.next();
            expressions.addLast(in.createExpression());
        }
        return expressions;
    }

    protected List<MgExpression> createExpressions(@Mandatory List<Node> input){
        List<MgExpression> expressions = new List<>();
        for(Node in : input){
            expressions.addLast(in.createExpression());
        }
        return expressions;
    }

    private static InputInterface createInputInterface(@Mandatory MgFunction function, int replication){
        Array<InputConnector> connectors = new Array<>(function.getInput().count());
        for(int i = 0; i < connectors.count(); i++){
            for(int r = 0; r < replication; r++){
                connectors.set(new InputConnector(function.getInput().get(i).getDatatype()), i);
            }
        }
        return new InputInterface(connectors);
    }

    private static OutputInterface createOutputInterface(@Mandatory MgFunction function, int replication){
        if(function == null) throw new RuntimeException();
        Array<OutputConnector> connectors = new Array<>(function.getOutput().count());
        for(int i = 0; i < connectors.count(); i++){
            for(int r = 0; r < replication; r++){
                connectors.set(new OutputConnector(function.getOutput().get(i).getDatatype()), i);
            }
        }
        return new OutputInterface(connectors);
    }

    protected Array<Integer> gatherLeftInputOffset(){
        Array<Integer> offset = new Array<>(replication);
        for(int i = 0; i < replication; i++){
            InputConnector in = getInputInterface().getConnectors().get(i);
            offset.set(i, in.getConnection().getConnectionVariable().getOffset());
        }
        return offset;
    }

    protected Array<Integer> gatherRightInputOffset(){
        Array<Integer> offset = new Array<>(replication);
        for(int i = replication; i < 2*replication; i++){
            InputConnector in = getInputInterface().getConnectors().get(i);
            offset.set(i - replication, in.getConnection().getConnectionVariable().getOffset());
        }
        return offset;
    }

    protected Array<Integer> gatherInputOffset(){
        Array<Integer> offset = new Array<>(getInputInterface().getConnectors().count());
        for(int i = 0; i < getInputInterface().getConnectors().count(); i++){
            InputConnector in = getInputInterface().getConnectors().get(i);
            offset.set(i, in.getConnection().getConnectionVariable().getOffset());
        }
        return offset;
    }

    protected Array<Integer> gatherOutputOffset(){
        Array<Integer> offset = new Array<>();
        for(int i = 0; i < getOutputInterface().getConnectors().count(); i++){
            OutputConnector out = getOutputInterface().getConnectors().get(i);
            offset.set(i, out.getConnection().getConnectionVariable().getOffset());
        }
        return offset;
    }
}
