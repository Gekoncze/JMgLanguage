package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgExceptionType;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class ExceptionNode extends Node {
    private static final MgDatatype DATATYPE = new MgDatatype(MgExceptionType.getInstance(), MgDatatype.Storage.OTHER, MgDatatype.Requirement.MANDATORY);

    public ExceptionNode() {
        super(createInputInterface(), createOutputInterface());
    }

    private static InputInterface createInputInterface(){
        return new InputInterface(new Array<>(new InputConnector(DATATYPE)));
    }

    private static OutputInterface createOutputInterface(){
        return new OutputInterface(new Array<>());
    }
}
