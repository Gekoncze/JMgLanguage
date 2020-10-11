package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Shared;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;


public abstract class Connector {
    @Mandatory @Value
    private final MgDatatype requestedDatatype;

    @Optional @Shared
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
        if(connection == null) throw new LanguageException("Missing connection.");
    }
}
