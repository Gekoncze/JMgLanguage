package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Part;


public abstract class Interface<InterfaceConnector extends Connector> {
    @Part
    private final Array<InterfaceConnector> connectors;

    Interface(Array<InterfaceConnector> connectors) {
        this.connectors = connectors;
    }

    public void validate(){
        for(InterfaceConnector connector : connectors){
            if(connector == null) throw new RuntimeException();
            connector.validate();
        }
    }
}
