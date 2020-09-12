package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.requirement.Mandatory;


public class InputInterface extends Interface<InputConnector> {
    public InputInterface(@Mandatory Array array) {
        super(array);
    }
}
