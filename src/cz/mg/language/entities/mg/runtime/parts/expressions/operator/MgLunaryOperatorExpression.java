package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.Pass;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.special.PartCollection;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgLunaryOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.DeclarationHelper;


public class MgLunaryOperatorExpression extends MgUnaryOperatorExpression {
    @Mandatory @Part
    private final MgExpression rightExpression;

    public MgLunaryOperatorExpression(List<MgReplication> replications, MgExpression rightExpression) {
        super(replications);
        this.rightExpression = rightExpression;
        connect();
    }

    private void connect(){
        Pass<MgInputConnector> inputConnectorPass = new PartCollection<>(
            getReplications(),
            replication -> getInputConnectors(replication).getFirst()
        ).iterator();

        Pass<MgOutputConnector> outputConnectorPass = getOutputConnectors(rightExpression).iterator();

        while(inputConnectorPass.hasNext() && outputConnectorPass.hasNext()){
            DeclarationHelper.connect(inputConnectorPass.next(), outputConnectorPass.next());
        }
    }

    @Override
    protected @Mandatory ReadableList<MgExpression> getExpressions() {
        return new List<>(rightExpression);
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        rightExpression.run(functionInstance);
        super.run(functionInstance);
    }

    public static class MgReplication extends MgUnaryOperatorExpression.MgReplication {
        public MgReplication(MgLunaryOperator operator) {
            super(operator);
        }
    }
}
