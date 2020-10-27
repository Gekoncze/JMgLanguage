package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.function.MgFunctionExpression;


public abstract class MgOperatorExpression extends MgExpression {
    @Mandatory @Part
    private final ReadableArray<@Mandatory @Link MgReplication> replications;

    public MgOperatorExpression(ReadableCollection<MgOperator> operators) {
        this(createReplications(operators));
    }

    private MgOperatorExpression(ReadableArray<MgReplication> replications){
        super(
            gatherInputConnectors(replications),
            gatherOutputConnectors(replications)
        );
        this.replications = replications;
    }

    public ReadableArray<MgReplication> getReplications() {
        return replications;
    }

    @Override
    protected void onRun(MgFunctionInstance functionInstance) {
        for(MgReplication replication : replications){
            replication.run(functionInstance);
        }
    }

    public static class MgReplication extends MgFunctionExpression {
        public MgReplication(MgOperator operator) {
            super(
                MgFunctionExpression.createInputConnectors(operator),
                MgFunctionExpression.createOutputConnectors(operator),
                operator
            );
        }

        @Override
        public List<MgExpression> getExpressions() {
            throw new RuntimeException();
        }
    }

    private static ReadableArray<MgInputConnector> gatherInputConnectors(ReadableArray<? extends MgReplication> replications){
        List<MgInputConnector> inputConnectors = new List<>();
        for(MgReplication replication : replications){
            inputConnectors.addCollectionLast(replication.getInputConnectors());
        }
        return new Array<>(inputConnectors);
    }

    private static ReadableArray<MgOutputConnector> gatherOutputConnectors(ReadableArray<? extends MgReplication> replications){
        List<MgOutputConnector> outputConnectors = new List<>();
        for(MgReplication replication : replications){
            outputConnectors.addCollectionLast(replication.getOutputConnectors());
        }
        return new Array<>(outputConnectors);
    }
    
    private static ReadableArray<MgReplication> createReplications(ReadableCollection<MgOperator> operators){
        Array<MgReplication> replications = new Array<>(operators.count());
        int i = 0;
        for(MgOperator operator : operators){
            replications.set(new MgReplication(operator), i);
            i++;
        }
        return replications;
    }
}
