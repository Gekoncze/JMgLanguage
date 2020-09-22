package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.atoms.MgTextObject;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.Node;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class ValueNode extends Node {
    private static final MgDatatype DATATYPE = new MgDatatype(MgTextObject.TYPE, MgDatatype.Storage.DIRECT, MgDatatype.Requirement.MANDATORY);

    @Mandatory @Part
    private final MgTextObject value;

    public ValueNode(@Mandatory MgTextObject value) {
        super(createInputInterface(value), createOutputInterface(value));
        this.value = value;
    }

    public MgTextObject getValue() {
        return value;
    }

    private static InputInterface createInputInterface(@Mandatory MgTextObject value){
        return new InputInterface(new Array<>()); // no input for variables
    }

    private static OutputInterface createOutputInterface(@Mandatory MgTextObject variable){
        return new OutputInterface(new Array<>(new OutputConnector(DATATYPE)));
    }
}
