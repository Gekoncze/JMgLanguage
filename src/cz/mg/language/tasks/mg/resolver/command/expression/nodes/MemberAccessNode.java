package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.collections.array.Array;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.mg.runtime.components.types.MgClass;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.*;


public class MemberAccessNode extends Node {
    public MemberAccessNode(OutputInterface leftOutputInterface, OutputInterface rightOutputInterface) {
        super(createInputInterface(leftOutputInterface), createOutputInterface(rightOutputInterface));
    }

    private static InputInterface createInputInterface(OutputInterface leftOutputInterface){
        if(leftOutputInterface.getConnectors().count() < 1){
            throw new LanguageException("Cannot access member of an expression, because it does not return any value.");
        }

        if(leftOutputInterface.getConnectors().count() > 1){
            throw new LanguageException("Cannot access member of an expression, because it returns multiple values.");
        }

        MgDatatype datatype = leftOutputInterface.getConnectors().getFirst().getRequestedDatatype();
        if(!(datatype.getType() instanceof MgClass)){
            throw new LanguageException("Cannot access member of " + datatype.getType().getName() + ".");
        }

        return new InputInterface(new Array<>(new InputConnector(datatype)));
    }

    private static OutputInterface createOutputInterface(OutputInterface rightOutputInterface){
        Array<OutputConnector> connectors = new Array<>(rightOutputInterface.getConnectors().count());
        for(int i = 0; i < rightOutputInterface.getConnectors().count(); i++){
            connectors.set(new OutputConnector(rightOutputInterface.getConnectors().get(i).getRequestedDatatype()), i);
        }
        return new OutputInterface(connectors);
    }
}
