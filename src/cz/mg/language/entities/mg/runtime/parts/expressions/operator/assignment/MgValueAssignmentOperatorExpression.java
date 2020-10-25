package cz.mg.language.entities.mg.runtime.parts.expressions.operator.assignment;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgValueAssignmentOperatorExpression extends MgAssignmentOperatorExpression {
    @Mandatory @Link
    private final ReadableArray<Replication> replications;

    public MgValueAssignmentOperatorExpression(
        MgExpression leftExpression,
        MgExpression rightExpression,
        List<MgOperator> operators
    ) {
        super(createInputInterface(operators), leftExpression, rightExpression);
        this.replications = createReplications(operators);
    }

    public ReadableArray<Replication> getReplications() {
        return replications;
    }

    @Override
    protected MgCache createCache() {
        new Todo();
        return null;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        super.run(functionInstance);
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

        public Replication(
            MgOperator operator,
            MgInputConnector leftInput,
            MgInputConnector rightInput
        ) {
            this.operator = operator;
            this.leftInput = leftInput;
            this.rightInput = rightInput;
            if(operator.getInputVariables().count() != 2) throw new RuntimeException();
            if(operator.getOutputVariables().count() != 0) throw new RuntimeException();
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
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(leftInput.getConnection().getConnectionVariable().getCache().getOffset()), 0);
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(rightInput.getConnection().getConnectionVariable().getCache().getOffset()), 1);

            // run the function
            operator.run(newFunctionObject);
        }
    }

    private static ReadableArray<MgInputConnector> createInputInterface(@Mandatory List<MgOperator> operator){
        Array<MgInputConnector> connectors = new Array<>(operator.count() * 2);
        int i = 0;
        for(MgOperator function : operator){
            connectors.set(new MgInputConnector(function.getInputVariables().getFirst().getDatatype()), i);
            i++;
        }
        for(MgOperator function : operator){
            connectors.set(new MgInputConnector(function.getInputVariables().getLast().getDatatype()), i);
            i++;
        }
        return connectors;
    }

    private ReadableArray<Replication> createReplications(List<MgOperator> operators){
        Array<Replication> replications = new Array<>(operators.count());
        new Todo();
//        int i = 0;
//        for(MgOperator operator : operators){
//            replications.set(new Replication(
//                operator,
//                getInputConnectors().get(i),
//                getInputConnectors().get(i + operators.count())
//            ), i);
//            i++;
//        }
        return replications;
    }
}
