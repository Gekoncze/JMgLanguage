package cz.mg.language.entities.mg.runtime.parts.expressions.operator;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.language.Todo;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgOperator;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgUnaryOperatorExpression extends MgOperatorExpression {
    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Part
    private final ReadableArray<Replication> replications;

    public MgUnaryOperatorExpression(MgExpression expression, List<MgOperator> operators) {
//        super(createInputInterface(operators), createOutputInterface(operators));
        new Todo();
        this.expression = expression;
        this.replications = createReplications(operators);
    }

    public MgExpression getExpression() {
        return expression;
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
        if(DEBUG) validate();

        expression.run(functionInstance);

        for(Replication replication : replications){
            replication.run(functionInstance);
        }
    }

    public static class Replication implements MgRunnable {
        @Mandatory @Link
        private final MgOperator operator;

        @Mandatory @Link
        private final MgInputConnector inputConnector;

        @Mandatory @Link
        private final MgOutputConnector outputConnector;

        public Replication(
            MgOperator operator,
            MgInputConnector inputConnector,
            MgOutputConnector outputConnector
        ) {
            this.operator = operator;
            this.inputConnector = inputConnector;
            this.outputConnector = outputConnector;
            if(operator.getInputVariables().count() != 1) throw new RuntimeException();
            if(operator.getOutputVariables().count() != 1) throw new RuntimeException();
        }

        public MgOperator getOperator() {
            return operator;
        }

        public MgInputConnector getInputConnector() {
            return inputConnector;
        }

        @Override
        public void run(MgFunctionInstance functionInstance) {
            // create new function object
            MgFunctionInstanceImpl newFunctionObject = new MgFunctionInstanceImpl(operator);

            // set input for newly created function object
            MgInstanceVariable inputVariable = inputConnector.getConnection().getConnectionVariable();
            MgObject inputObject = functionInstance.getObjects().get(inputVariable.getCache().getOffset());
            newFunctionObject.getObjects().set(inputObject, 0);

            // run the function
            operator.run(newFunctionObject);

            // get and store output of the executed function object
            MgInstanceVariable outputVariable = outputConnector.getConnection().getConnectionVariable();
            MgObject outputObject = newFunctionObject.getObjects().get(1);
            functionInstance.getObjects().set(outputObject, outputVariable.getCache().getOffset());
        }
    }

    private static ReadableArray<MgInputConnector> createInputInterface(@Mandatory List<MgOperator> functions){
        Array<MgInputConnector> connectors = new Array<>(functions.count());
        int i = 0;
        for(MgOperator function : functions){
            connectors.set(new MgInputConnector(function.getInputVariables().getFirst().getDatatype()), i);
            i++;
        }
        return connectors;
    }

    private static ReadableArray<MgOutputConnector> createOutputInterface(@Mandatory List<MgOperator> functions){
        Array<MgOutputConnector> connectors = new Array<>(functions.count());
        int i = 0;
        for(MgOperator function : functions){
            connectors.set(new MgOutputConnector(function.getOutputVariables().getFirst().getDatatype()), i);
            i++;
        }
        return connectors;
    }

    private ReadableArray<Replication> createReplications(List<MgOperator> operators) {
        Array<Replication> replications = new Array<>(operators.count());
        new Todo();
        int i = 0;
        for(MgOperator operator : operators){
            replications.set(new Replication(
                operator,
                getInputConnectors().get(i),
                getOutputConnectors().get(i)
            ), i);
            i++;
        }
        return replications;
    }
}
