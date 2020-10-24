package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
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

    public MgFunctionExpression(
        MgFunction function,
        MgExpression expression
    ) {
        super(createInputInterface(function), createOutputInterface(function));
        this.function = function;
        this.expression = expression;
    }

    public MgFunction getFunction() {
        return function;
    }

    public MgExpression getExpression() {
        return expression;
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
            MgFunctionVariable in = inputConnector.getConnection().getConnectionVariable();
            newFunctionObject.getObjects().set(functionInstance.getObjects().get(in.getOffset()), local);
            local++;
        }

        // run the function
        function.run(functionInstance);

        // get output of the newly created function object
        for(MgOutputConnector outputConnector : getOutputConnectors()){
            MgFunctionVariable out = outputConnector.getConnection().getConnectionVariable();
            functionInstance.getObjects().set(newFunctionObject.getObjects().get(local), out.getOffset());
            local++;
        }
    }

    public static ReadableArray<MgInputConnector> createInputInterface(@Mandatory MgFunction function){
        Array<MgInputConnector> connectors = new Array<>(function.getInput().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new MgInputConnector(function.getInput().get(i).getDatatype()), i);
        }
        return connectors;
    }

    public static ReadableArray<MgOutputConnector> createOutputInterface(@Mandatory MgFunction function){
        if(function == null) throw new RuntimeException();
        Array<MgOutputConnector> connectors = new Array<>(function.getOutput().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new MgOutputConnector(function.getOutput().get(i).getDatatype()), i);
        }
        return connectors;
    }
}
