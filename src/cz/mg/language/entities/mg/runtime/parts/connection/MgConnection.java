package cz.mg.language.entities.mg.runtime.parts.connection;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public class MgConnection {
    @Mandatory @Link
    private final MgInstanceVariable connectionVariable;

    private MgConnection(@Mandatory @Link MgInstanceVariable connectionVariable) {
        this.connectionVariable = connectionVariable;
    }

    public MgInstanceVariable getConnectionVariable() {
        return connectionVariable;
    }

    public static void connect(
        @Mandatory MgInputConnector inputConnector,
        @Mandatory MgInstanceVariable connectionVariable,
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
}
