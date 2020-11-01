package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.Pass;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.special.PartCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgBinaryOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.DeclarationHelper;


public class MgBinaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final MgExpression leftExpression;

    @Mandatory @Part
    private final MgExpression rightExpression;

    public MgBinaryOperatorExpression(
        List<MgReplication> replications,
        MgExpression leftExpression,
        MgExpression rightExpression
    ) {
        super(replications);
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        connect();
    }

    private void connect(){
        Pass<MgInputConnector> leftInputConnectorPass = new PartCollection<>(
            getReplications(),
            replication -> replication.getInputConnectors().getFirst()
        ).iterator();

        Pass<MgOutputConnector> leftOutputConnectorPass = leftExpression.getOutputConnectors().iterator();

        while(leftInputConnectorPass.hasNext() && leftOutputConnectorPass.hasNext()){
            DeclarationHelper.connect(leftInputConnectorPass.next(), leftOutputConnectorPass.next());
        }

        Pass<MgInputConnector> rightInputConnectorPass = new PartCollection<>(
            getReplications(),
            replication -> replication.getInputConnectors().getLast()
        ).iterator();

        Pass<MgOutputConnector> rightOutputConnectorPass = rightExpression.getOutputConnectors().iterator();

        while(rightInputConnectorPass.hasNext() && rightOutputConnectorPass.hasNext()){
            DeclarationHelper.connect(rightInputConnectorPass.next(), rightOutputConnectorPass.next());
        }
    }

    @Override
    public ReadableList<MgExpression> getExpressions() {
        return new List<>(leftExpression, rightExpression);
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        leftExpression.run(functionInstance);
        rightExpression.run(functionInstance);
        super.run(functionInstance);
    }

    public static class MgReplication extends MgOperatorExpression.MgReplication {
        public MgReplication(MgBinaryOperator operator) {
            super(checkOutput(checkInput(operator)));
        }

        private static MgBinaryOperator checkInput(MgBinaryOperator operator){
            if(operator.getInputVariables().count() != 2){
                throw new LanguageException("Illegal input count for binary operator.");
            } else {
                return operator;
            }
        }

        private static MgBinaryOperator checkOutput(MgBinaryOperator operator){
            if(operator.getOutputVariables().count() != 1){
                throw new LanguageException("Illegal output count for binary operator.");
            } else {
                return operator;
            }
        }
    }
}
