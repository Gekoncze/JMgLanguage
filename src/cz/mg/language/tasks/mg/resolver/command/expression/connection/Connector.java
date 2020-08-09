package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Shared;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public abstract class Connector {
    @Link
    private final MgDatatype requestedDatatype;

    @Shared
    private Connection connection;

    Connector(MgDatatype requestedDatatype) {
        this.requestedDatatype = requestedDatatype;
    }

    public MgDatatype getRequestedDatatype() {
        return requestedDatatype;
    }

    public Connection getConnection() {
        return connection;
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void validate(){
        if(connection == null) throw new RuntimeException();
    }
}
