package cz.mg.language.tasks.mg.resolver.command.expression.connection.nodes;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgFunctionExpression;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.*;


public class FunctionNode extends Node {
    @Mandatory @Link
    private final MgFunction function;

    public FunctionNode(@Mandatory MgFunction function) {
        super(createInputInterface(function), createOutputInterface(function));
        this.function = function;
    }

    @Override
    public MgFunctionExpression createExpression(){
        return new MgFunctionExpression(
            function,
            createExpressions(),
            gatherInputOffset(),
            gatherOutputOffset()
        );
    }

    private MgExpression createExpressions(){
        List<MgExpression> expressions = new List<>();
        for(Node in : getChildren()){
            expressions.addLast(in.createExpression());
        }
        if(expressions.count() < 0){
            return null;
        } else if(expressions.count() == 1){
            return expressions.getFirst();
        } else {
            throw new RuntimeException();
        }
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

    private List<Integer> gatherInputOffset(){
        List<Integer> offset = new List<>();
        for(InputConnector in : getInputInterface().getConnectors()){
            offset.addLast(in.getConnection().getConnectionVariable().getOffset());
        }
        return offset;
    }

    private List<Integer> gatherOutputOffset(){
        List<Integer> offset = new List<>();
        for(OutputConnector out : getOutputInterface().getConnectors()){
            offset.addLast(out.getConnection().getConnectionVariable().getOffset());
        }
        return offset;
    }
}
