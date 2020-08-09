package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;


public class InputInterface extends Interface<InputConnector> {
    InputInterface(Array array) {
        super(array);
    }

    public static InputInterface create(MgVariable variable){
        if(variable == null) throw new RuntimeException();
        return new InputInterface(new Array<>()); // no input for variables
    }

    public static InputInterface create(MgFunction function){
        if(function == null) throw new RuntimeException();
        Array<InputConnector> connectors = new Array<>(function.getInput().count());
        for(int i = 0; i < connectors.count(); i++){
            connectors.set(new InputConnector(function.getInput().get(i).getDatatype()), i);
        }
        return new InputInterface(connectors);
    }
}
