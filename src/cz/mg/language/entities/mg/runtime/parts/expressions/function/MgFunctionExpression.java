package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.Pass;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public abstract class MgFunctionExpression extends MgExpression {
    @Mandatory @Part
    private final MgFunction function;

    public MgFunctionExpression(
        ReadableArray<MgInputConnector> inputConnectors,
        ReadableArray<MgOutputConnector> outputConnectors,
        MgFunction function
    ) {
        super(inputConnectors, outputConnectors);
        this.function = function;
    }

    public MgFunction getFunction() {
        return function;
    }

    public static void setFunctionInstanceInput(
        MgFunctionInstance functionInstance,
        ReadableArray<MgInputConnector> inputConnectors,
        MgFunctionInstance newFunctionInstance
    ){
        Pass<MgInstanceVariable> input = newFunctionInstance.getType().getInputVariables().iterator();
        for(MgInputConnector inputConnector : inputConnectors){
            MgInstanceVariable inputVariable = inputConnector.getConnection().getConnectionVariable();
            MgObject inputObject = functionInstance.getObjects().get(inputVariable.getCache().getOffset());
            newFunctionInstance.getObjects().set(inputObject, input.next().getCache().getOffset());
        }
    }

    public static void getFunctionInstanceOutput(
        MgFunctionInstance functionInstance,
        ReadableArray<MgOutputConnector> outputConnectors,
        MgFunctionInstance newFunctionInstance
    ){
        Pass<MgInstanceVariable> output = newFunctionInstance.getType().getOutputVariables().iterator();
        for(MgOutputConnector outputConnector : outputConnectors){
            MgInstanceVariable outputVariable = outputConnector.getConnection().getConnectionVariable();
            MgObject outputObject = newFunctionInstance.getObjects().get(output.next().getCache().getOffset());
            functionInstance.getObjects().set(outputObject, outputVariable.getCache().getOffset());
        }
    }

    public static ReadableArray<MgInputConnector> createInputConnectors(MgFunction function){
        Array<MgInputConnector> connectors = new Array<>(function.getInputVariables().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new MgInputConnector(function.getInputVariables().get(i).getDatatype()), i);
        }
        return connectors;
    }

    public static ReadableArray<MgOutputConnector> createOutputConnectors(MgFunction function){
        if(function == null) throw new RuntimeException();
        Array<MgOutputConnector> connectors = new Array<>(function.getOutputVariables().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new MgOutputConnector(function.getOutputVariables().get(i).getDatatype()), i);
        }
        return connectors;
    }

    @Override
    public void onRun(MgFunctionInstance functionInstance) {
        MgFunctionInstance newFunctionInstance = new MgFunctionInstanceImpl(function);
        setFunctionInstanceInput(functionInstance, getInputConnectors(), newFunctionInstance);
        function.run(functionInstance);
        getFunctionInstanceOutput(functionInstance, getOutputConnectors(), newFunctionInstance);
    }
}
