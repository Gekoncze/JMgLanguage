package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ArrayView;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;


public abstract class Interface<InterfaceConnector extends Connector> {
    @Mandatory @Part
    private final Array<InterfaceConnector> connectors;

    Interface(@Mandatory Array<InterfaceConnector> connectors) {
        this.connectors = connectors;
    }

    public ReadableArray<InterfaceConnector> getConnectors() {
        return connectors;
    }

    public ReadableArray<InterfaceConnector> getRemainingConnectors(){
        int offset;
        for(offset = 0; offset < connectors.count(); offset++){
            if(connectors.get(offset).getConnection() != null){
                break;
            }
        }
        return new ArrayView<>(connectors, offset, connectors.count());
    }

    public void validate(){
        for(InterfaceConnector connector : connectors){
            connector.validate();
        }
    }
}
