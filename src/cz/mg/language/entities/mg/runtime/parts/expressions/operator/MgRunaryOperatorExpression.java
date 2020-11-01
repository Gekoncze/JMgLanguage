package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.Pass;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.special.PartCollection;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgRunaryOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.DeclarationHelper;


public class MgRunaryOperatorExpression extends MgUnaryOperatorExpression {
    @Mandatory @Part
    private final MgExpression leftExpression;

    public MgRunaryOperatorExpression(List<MgReplication> replications, MgExpression leftExpression) {
        super(replications);
        this.leftExpression = leftExpression;
        connect();
    }

    private void connect(){
        Pass<MgInputConnector> inputConnectorPass = new PartCollection<>(
            getReplications(),
            replication -> replication.getInputConnectors().getFirst()
        ).iterator();

        Pass<MgOutputConnector> outputConnectorPass = leftExpression.getOutputConnectors().iterator();

        while(inputConnectorPass.hasNext() && outputConnectorPass.hasNext()){
            DeclarationHelper.connect(inputConnectorPass.next(), outputConnectorPass.next());
        }
    }

    @Override
    public @Mandatory ReadableList<MgExpression> getExpressions() {
        return new List<>(leftExpression);
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        leftExpression.run(functionInstance);
        super.run(functionInstance);
    }

    public static class MgReplication extends MgUnaryOperatorExpression.MgReplication {
        public MgReplication(MgRunaryOperator operator) {
            super(operator);
        }
    }
}
