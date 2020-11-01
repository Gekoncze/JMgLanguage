package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgGroupExpression extends MgExpression {
    @Mandatory @Part
    private final List<@Mandatory @Part MgExpression> expressions;

    @Mandatory @Part
    private final List<@Mandatory @Link MgOutputConnector> outputConnectors;

    private MgGroupExpression(List<MgExpression> expressions) {
        this.expressions = expressions;
        outputConnectors = gatherOutputConnectors(expressions);
    }

    @Override
    public ReadableList<MgExpression> getExpressions() {
        return expressions;
    }

    @Override
    protected ReadableCollection<MgInputConnector> getInputConnectors() {
        return new Array<>();
    }

    @Override
    protected ReadableList<MgOutputConnector> getOutputConnectors() {
        return outputConnectors;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        for(MgExpression expression : expressions){
            expression.run(functionInstance);
        }
    }

    private static List<MgOutputConnector> gatherOutputConnectors(List<MgExpression> expressions){
        List<MgOutputConnector> outputConnectors = new List<>();
        for(MgExpression expression : expressions){
            for(MgOutputConnector outputConnector : expression.getOutputConnectors()){
                outputConnectors.addLast(outputConnector);
            }
        }
        return outputConnectors;
    }
}
