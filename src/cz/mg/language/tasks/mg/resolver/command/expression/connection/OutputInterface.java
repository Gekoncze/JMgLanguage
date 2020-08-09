package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;


public class OutputInterface extends Interface<OutputConnector> {
    OutputInterface(Array array) {
        super(array);
    }

    public static OutputInterface create(MgVariable variable){
        if(variable == null) throw new RuntimeException();
        return new OutputInterface(new Array<>(new OutputConnector(variable.getDatatype())));
    }

    public static OutputInterface create(MgFunction function){
        if(function == null) throw new RuntimeException();
        Array<OutputConnector> connectors = new Array<>(function.getOutput().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new OutputConnector(function.getOutput().get(i).getDatatype()), i);
        }
        return new OutputInterface(connectors);
    }
}
