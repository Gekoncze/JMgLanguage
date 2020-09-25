package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.atoms.MgBoolObject;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class BooleanNode extends Node {
    private static final MgDatatype DATATYPE = new MgDatatype(MgBoolObject.TYPE, MgDatatype.Storage.DIRECT, MgDatatype.Requirement.MANDATORY);

    public BooleanNode() {
        super(createInputInterface(), createOutputInterface());
    }

    private static InputInterface createInputInterface(){
        return new InputInterface(new Array<>(new InputConnector(DATATYPE)));
    }

    private static OutputInterface createOutputInterface(){
        return new OutputInterface(new Array<>());
    }
}
