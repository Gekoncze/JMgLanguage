package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgBinaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final MgExpression leftExpression;

    @Mandatory @Part
    private final MgExpression rightExpression;

    @Mandatory @Part
    private final ReadableArray<Replication> replications;

    public MgBinaryOperatorExpression(
        MgExpression leftExpression,
        MgExpression rightExpression,
        List<MgOperator> operators
    ) {
        super(createInputInterface(operators), createOutputInterface(operators));
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.replications = createReplications(operators);
    }

    public MgExpression getLeftExpression() {
        return leftExpression;
    }

    public MgExpression getRightExpression() {
        return rightExpression;
    }

    public ReadableArray<Replication> getReplications() {
        return replications;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        leftExpression.run(functionInstance);
        rightExpression.run(functionInstance);

        for(Replication replication : replications){
            replication.run(functionInstance);
        }
    }

    public static class Replication implements MgRunnable {
        @Mandatory @Link
        private final MgOperator operator;

        @Mandatory @Link
        private final MgInputConnector leftInput;

        @Mandatory @Link
        private final MgInputConnector rightInput;

        @Mandatory @Link
        private final MgOutputConnector output;

        public Replication(
            MgOperator operator,
            MgInputConnector leftInput,
            MgInputConnector rightInput,
            MgOutputConnector output
        ) {
            this.operator = operator;
            this.leftInput = leftInput;
            this.rightInput = rightInput;
            this.output = output;
            if(operator.getInput().count() != 2) throw new RuntimeException();
            if(operator.getOutput().count() != 1) throw new RuntimeException();
        }

        public MgOperator getOperator() {
            return operator;
        }

        @Override
        public void run(MgFunctionInstance functionInstance) {
            // Note: It is guaranteed that for every function object
            //       input variables are first in their order and then output variables in their order.

            // create new function object
            MgFunctionInstanceImpl newFunctionObject = new MgFunctionInstanceImpl(operator);

            // set input for newly created function object
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(leftInput.getConnection().getConnectionVariable().getOffset()), 0);
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(rightInput.getConnection().getConnectionVariable().getOffset()), 1);

            // run the function
            operator.run(newFunctionObject);

            // get and store output of the executed function object
            functionInstance.getObjects().set(newFunctionObject.getObjects().get(2), output.getConnection().getConnectionVariable().getOffset());
        }
    }

    private static ReadableArray<MgInputConnector> createInputInterface(@Mandatory List<MgOperator> operator){
        Array<MgInputConnector> connectors = new Array<>(operator.count() * 2);
        int i = 0;
        for(MgOperator function : operator){
            connectors.set(new MgInputConnector(function.getInput().getFirst().getDatatype()), i);
            i++;
        }
        for(MgOperator function : operator){
            connectors.set(new MgInputConnector(function.getInput().getLast().getDatatype()), i);
            i++;
        }
        return connectors;
    }

    private static ReadableArray<MgOutputConnector> createOutputInterface(@Mandatory List<MgOperator> functions){
        Array<MgOutputConnector> connectors = new Array<>(functions.count());
        int i = 0;
        for(MgOperator function : functions){
            connectors.set(new MgOutputConnector(function.getOutput().getFirst().getDatatype()), i);
            i++;
        }
        return connectors;
    }

    private ReadableArray<Replication> createReplications(List<MgOperator> operators){
        Array<Replication> replications = new Array<>(operators.count());
        int i = 0;
        for(MgOperator operator : operators){
            replications.set(new Replication(
                operator,
                getInputConnectors().get(i),
                getInputConnectors().get(i + operators.count()),
                getOutputConnectors().get(i)
            ), i);
            i++;
        }
        return replications;
    }
}
