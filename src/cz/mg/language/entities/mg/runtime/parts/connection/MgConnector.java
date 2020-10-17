package cz.mg.language.entities.mg.runtime.parts.connection;

import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Shared;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public abstract class MgConnector {
    @Mandatory @Link
    private final MgDatatype datatype;

    @Optional @Shared
    private MgConnection connection;

    public MgConnector(MgDatatype datatype) {
        this.datatype = datatype;
    }

    public MgDatatype getDatatype() {
        return datatype;
    }

    public MgConnection getConnection() {
        return connection;
    }

    protected void setConnection(MgConnection connection) {
        this.connection = connection;
    }

    public void validate(){
        if(connection == null){
            throw new RuntimeException("Connector validation failed.");
        }
    }
}
