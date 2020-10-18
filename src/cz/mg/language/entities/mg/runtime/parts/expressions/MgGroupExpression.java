package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgGroupExpression extends MgExpression {
    @Mandatory @Part
    private final List<MgExpression> expressions = new List<>();

    private MgGroupExpression(ReadableArray<? extends MgConnector> connectors) {
        super(createInputInterface(connectors), createOutputInterface(connectors));
        todo;
    }

    public List<MgExpression> getExpressions() {
        return expressions;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        for(MgExpression expression : expressions){
            expression.run(functionInstance);
        }
    }

    private static ReadableArray<MgInputConnector> createInputInterface(ReadableArray<? extends MgConnector> connectors){
        Array<MgInputConnector> copies = new Array<>(connectors.count());
        for(int i = 0; i < connectors.count(); i++){
            copies.set(new MgInputConnector(connectors.get(i).getDatatype()), i);
        }
        return copies;
    }

    private static ReadableArray<MgOutputConnector> createOutputInterface(ReadableArray<? extends MgConnector> connectors){
        Array<MgOutputConnector> copies = new Array<>(connectors.count());
        for(int i = 0; i < connectors.count(); i++){
            copies.set(new MgOutputConnector(connectors.get(i).getDatatype()), i);
        }
        return copies;
    }
}
