package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.annotations.storage.Shared;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgFunctionExpression extends MgExpression {
    @Mandatory @Part
    private final MgFunction function;

    @Optional @Part
    private final MgExpression expression;

    @Mandatory @Shared
    private final ReadableArray<MgInputConnector> inputConnectors;

    @Mandatory @Shared
    private final ReadableArray<MgOutputConnector> outputConnectors;

    public MgFunctionExpression(MgFunction function, MgExpression expression) {
        this.function = function;
        this.expression = expression;
        this.inputConnectors = createInputConnectors(function);
        this.outputConnectors = createOutputConnectors(function);
    }

    public MgFunction getFunction() {
        return function;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public ReadableArray<MgInputConnector> getInputConnectors() {
        return inputConnectors;
    }

    public ReadableArray<MgOutputConnector> getOutputConnectors() {
        return outputConnectors;
    }

    @Override
    protected MgCache createCache() {
        return new MgCache(inputConnectors, outputConnectors);
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        if(expression!= null){
            expression.run(functionInstance);
        }

        int local = 0;

        // create new function object
        MgFunctionInstanceImpl newFunctionObject = new MgFunctionInstanceImpl(function);

        // set input for newly created function object
        for(MgInputConnector inputConnector : getInputConnectors()){
            MgInstanceVariable in = inputConnector.getConnection().getConnectionVariable();
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(in.getCache().getOffset()), local);
            local++;
        }

        // run the function
        function.run(functionInstance);

        // get output of the newly created function object
        for(MgOutputConnector outputConnector : getOutputConnectors()){
            MgInstanceVariable out = outputConnector.getConnection().getConnectionVariable();
            functionInstance.getObjects().set(newFunctionObject.getObjects().get(local), out.getCache().getOffset());
            local++;
        }
    }

    private static ReadableArray<MgInputConnector> createInputConnectors(@Mandatory MgFunction function){
        Array<MgInputConnector> connectors = new Array<>(function.getInputVariables().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new MgInputConnector(function.getInputVariables().get(i).getDatatype()), i);
        }
        return connectors;
    }

    private static ReadableArray<MgOutputConnector> createOutputConnectors(@Mandatory MgFunction function){
        if(function == null) throw new RuntimeException();
        Array<MgOutputConnector> connectors = new Array<>(function.getOutputVariables().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new MgOutputConnector(function.getOutputVariables().get(i).getDatatype()), i);
        }
        return connectors;
    }
}
