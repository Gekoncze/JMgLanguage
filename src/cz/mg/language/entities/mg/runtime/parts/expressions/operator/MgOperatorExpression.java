package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.parts.expressions.function.MgFunctionExpression;


public abstract class MgOperatorExpression extends MgExpression {
    @Mandatory @Part
    private final List<@Mandatory @Link ? extends MgReplication> replications;

    @Mandatory @Link
    private final List<MgInputConnector> inputConnectors;

    @Mandatory @Link
    private final List<MgOutputConnector> outputConnectors;

    public MgOperatorExpression(List<? extends MgReplication> replications) {
        this.replications = replications;
        this.inputConnectors = gatherInputConnectors(replications);
        this.outputConnectors = gatherOutputConnectors(replications);
    }

    public ReadableList<? extends MgReplication> getReplications() {
        return replications;
    }

    @Override
    public ReadableList<MgInputConnector> getInputConnectors() {
        return inputConnectors;
    }

    @Override
    public ReadableList<MgOutputConnector> getOutputConnectors() {
        return outputConnectors;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        for(MgReplication replication : replications){
            replication.run(functionInstance);
        }
    }

    public static abstract class MgReplication extends MgFunctionExpression {
        public MgReplication(MgOperator operator) {
            super(operator, new List<>());
        }
    }

    private static List<MgInputConnector> gatherInputConnectors(List<? extends MgReplication> replications){
        List<MgInputConnector> inputConnectors = new List<>();
        for(MgReplication replication : replications){
            inputConnectors.addCollectionLast(getInputConnectors(replication));
        }
        return inputConnectors;
    }

    private static List<MgOutputConnector> gatherOutputConnectors(List<? extends MgReplication> replications){
        List<MgOutputConnector> outputConnectors = new List<>();
        for(MgReplication replication : replications){
            outputConnectors.addCollectionLast(getOutputConnectors(replication));
        }
        return outputConnectors;
    }
}
