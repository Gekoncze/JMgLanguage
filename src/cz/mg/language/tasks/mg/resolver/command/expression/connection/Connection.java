package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;


public class Connection {
    @Mandatory @Link
    private final InputConnector inputConnector;

    @Mandatory @Link
    private final OutputConnector outputConnector;

    @Mandatory @Link
    private final MgVariable connectionVariable;

    private Connection(InputConnector inputConnector, OutputConnector outputConnector, MgVariable connectionVariable) {
        this.inputConnector = inputConnector;
        this.outputConnector = outputConnector;
        this.connectionVariable = connectionVariable;
    }

    public InputConnector getInputConnector() {
        return inputConnector;
    }

    public OutputConnector getOutputConnector() {
        return outputConnector;
    }

    public MgVariable getConnectionVariable() {
        return connectionVariable;
    }

    public static Connection connect(InputConnector inputConnector, OutputConnector outputConnector, MgVariable connectionVariable){
        if(outputConnector.getConnection() == inputConnector.getConnection()) return outputConnector.getConnection();
        if(outputConnector.getConnection() != null) throw new RuntimeException();
        if(inputConnector.getConnection() != null) throw new RuntimeException();
        if(!Matcher.matches(inputConnector.getRequestedDatatype(), connectionVariable.getDatatype())) throw new RuntimeException();
        if(!Matcher.matches(outputConnector.getRequestedDatatype(), connectionVariable.getDatatype())) throw new RuntimeException();
        Connection connection = new Connection(inputConnector, outputConnector, connectionVariable);
        inputConnector.setConnection(connection);
        outputConnector.setConnection(connection);
        return connection;
    }
}
