package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.storage.Shared;
import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgGroupExpression extends MgExpression {
    @Mandatory @Part
    private final List<MgExpression> expressions = new List<>();

    @Mandatory @Shared
    private final ReadableArray<MgInputConnector> inputConnectors;

    @Mandatory @Shared
    private final ReadableArray<MgOutputConnector> outputConnectors;

    private MgGroupExpression(ReadableArray<? extends MgConnector> connectors) {
        inputConnectors = createInputConnectors(connectors);
        outputConnectors = createOutputConnectors(connectors);
    }

    public List<MgExpression> getExpressions() {
        return expressions;
    }

    public ReadableArray<MgInputConnector> getInputConnectors() {
        return inputConnectors;
    }

    public ReadableArray<MgOutputConnector> getOutputConnectors() {
        return outputConnectors;
    }

    @Override
    protected MgCache createCache() {
        return new MgCache(getInputConnectors(), getOutputConnectors());
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        for(MgExpression expression : expressions){
            expression.run(functionInstance);
        }
    }

    private static ReadableArray<MgInputConnector> createInputConnectors(ReadableArray<? extends MgConnector> connectors){
        Array<MgInputConnector> copies = new Array<>(connectors.count());
        for(int i = 0; i < connectors.count(); i++){
            copies.set(new MgInputConnector(connectors.get(i).getDatatype()), i);
        }
        return copies;
    }

    private static ReadableArray<MgOutputConnector> createOutputConnectors(ReadableArray<? extends MgConnector> connectors){
        Array<MgOutputConnector> copies = new Array<>(connectors.count());
        for(int i = 0; i < connectors.count(); i++){
            copies.set(new MgOutputConnector(connectors.get(i).getDatatype()), i);
        }
        return copies;
    }
}
