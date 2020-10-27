package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgGroupExpression extends MgExpression {
    private MgGroupExpression(ReadableArray<? extends MgConnector> connectors) {
        super(
            createInputConnectors(connectors),
            createOutputConnectors(connectors)
        );
    }

    @Override
    public void onRun(MgFunctionInstance functionInstance) {
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
