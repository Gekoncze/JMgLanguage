package cz.mg.language.tasks.mg.resolver.command.expression.nodes;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.*;
import cz.mg.language.tasks.mg.resolver.command.utilities.VariableHelper;
import cz.mg.language.tasks.mg.resolver.context.CommandContext;


public class Node {
    @Mandatory @Part
    private final InputInterface inputInterface;

    @Mandatory @Part
    private final OutputInterface outputInterface;

    public Node(
        @Mandatory InputInterface inputInterface,
        @Mandatory OutputInterface outputInterface
    ) {
        this.inputInterface = inputInterface;
        this.outputInterface = outputInterface;
    }

    public InputInterface getInputInterface() {
        return inputInterface;
    }

    public OutputInterface getOutputInterface() {
        return outputInterface;
    }

    public void validate(){
        inputInterface.validate();
        outputInterface.validate();
    }

    public static void connect(
        @Mandatory CommandContext context,
        @Mandatory Node parent,
        @Mandatory Node child
    ){
        VariableHelper variableHelper = context.getVariableHelper();
        if(child.getOutputInterface() == null) throw new LanguageException("Cannot connect expressions. Unresolved child expression.");
        if(parent.getInputInterface() == null) throw new LanguageException("Cannot connect expressions. Unresolved parent expression.");
        for(OutputConnector outputConnector : child.getOutputInterface().getConnectors()){
            @Optional InputConnector inputConnector = parent.getInputInterface().getRemainingConnectors().getFirst();
            if(inputConnector == null) throw new LanguageException("Cannot connect expressions. Parent has not enough free connectors.");
            MgLocalVariable connectionVariable = variableHelper.nextExpressionVariable(parent, inputConnector, child, outputConnector);
            Connection.connect(inputConnector, outputConnector, connectionVariable);
        }
    }
}
