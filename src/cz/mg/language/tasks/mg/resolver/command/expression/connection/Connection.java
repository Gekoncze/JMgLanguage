package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.tasks.mg.resolver.command.expression.Matcher;


public class Connection {
    @Mandatory @Link
    private final MgFunctionVariable connectionVariable;

    private Connection(MgFunctionVariable connectionVariable) {
        this.connectionVariable = connectionVariable;
    }

    public MgFunctionVariable getConnectionVariable() {
        return connectionVariable;
    }

    public static void connect(
        @Mandatory InputConnector inputConnector, // parent input connector
        @Mandatory OutputConnector outputConnector, // child output connector
        @Mandatory MgFunctionVariable connectionVariable // variable connecting them
    ){
        // check existing connection
        if(outputConnector.getConnection() != null || inputConnector.getConnection() != null){
            if(outputConnector.getConnection() == inputConnector.getConnection()) return;
            else throw new RuntimeException();
        }

        // validate: variable = output
        if(!Matcher.matchesOptional(connectionVariable.getDatatype(), outputConnector.getRequestedDatatype())) throw new RuntimeException();

        // validate: input = variable
        if(!Matcher.matchesOptional(inputConnector.getRequestedDatatype(), connectionVariable.getDatatype())) throw new RuntimeException();

        // connect: input = variable = output
        Connection connection = new Connection(connectionVariable);
        inputConnector.setConnection(connection);
        outputConnector.setConnection(connection);
    }
}
