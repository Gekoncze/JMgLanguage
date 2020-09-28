package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.components.types.buildin.MgAtomType;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.components.types.MgType;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputConnector;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class ValueNode extends Node {
    @Mandatory @Part
    private final MgAtom value;

    public ValueNode(InputInterface parentInputInterface, ReadableText value) {
        super(createInputInterface(parentInputInterface), createOutputInterface(parentInputInterface));
        this.value = createValue(getOutputInterface().getConnectors().getFirst().getRequestedDatatype().getType(), value);
    }

    public MgAtom getValue() {
        return value;
    }

    private static InputInterface createInputInterface(InputInterface parentInputInterface){
        return new InputInterface(new Array<>()); // no input for variables
    }

    private static OutputInterface createOutputInterface(InputInterface parentInputInterface){
        if(parentInputInterface != null){
            ReadableArray<InputConnector> remainingInputConnectors = parentInputInterface.getRemainingConnectors();
            int count = remainingInputConnectors.count();
            if(count >= 1){
                InputConnector inputConnector = remainingInputConnectors.getFirst();
                return new OutputInterface(new Array<>(new OutputConnector(createDatatype(inputConnector))));
            } else {
                throw new LanguageException("Cannot connect expressions. Parent has no free connectors.");
            }
        } else {
            throw new LanguageException("Unknown value type.");
        }
    }

    private static MgDatatype createDatatype(InputConnector inputConnector){
        return new MgDatatype(
            inputConnector.getRequestedDatatype().getType(),
            inputConnector.getRequestedDatatype().getStorage(),
            MgDatatype.Requirement.MANDATORY
        );
    }

    private static MgAtom createValue(MgType type, ReadableText value){
        if(type instanceof MgAtomType){
            return ((MgAtomType) type).create(value);
        } else {
            throw new LanguageException("Expected atom type, given " + type.getName() + ".");
        }
    }
}
