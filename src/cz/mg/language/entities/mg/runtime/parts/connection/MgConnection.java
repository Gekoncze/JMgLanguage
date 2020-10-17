package cz.mg.language.entities.mg.runtime.parts.connection;

import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class MgConnection {
    @Mandatory @Link
    private final MgFunctionVariable connectionVariable;

    private MgConnection(@Mandatory @Link MgFunctionVariable connectionVariable) {
        this.connectionVariable = connectionVariable;
    }

    public MgFunctionVariable getConnectionVariable() {
        return connectionVariable;
    }

    public static void connect(
        @Mandatory MgInputConnector inputConnector,
        @Mandatory MgFunctionVariable connectionVariable,
        @Mandatory MgOutputConnector outputConnector
    ){
        if(inputConnector.getConnection() != null || outputConnector.getConnection() != null){
            throw new RuntimeException("Connect: Connector is already connected.");
        }

        if(!MgDatatype.isCompatible(connectionVariable.getDatatype(), outputConnector.getDatatype())){
            throw new RuntimeException("Connect: Incompatible output.");
        }

        if(!MgDatatype.isCompatible(inputConnector.getDatatype(), connectionVariable.getDatatype())){
            throw new RuntimeException("Connect: Incompatible input.");
        }

        MgConnection connection = new MgConnection(connectionVariable);
        inputConnector.setConnection(connection);
        outputConnector.setConnection(connection);
    }

    public static void disconnect(MgInputConnector inputConnector, MgOutputConnector outputConnector){
        if(inputConnector.getConnection() != outputConnector.getConnection()){
            throw new RuntimeException("Disconnect: Connectors are not connected.");
        }

        inputConnector.setConnection(null);
        outputConnector.setConnection(null);
    }
}
