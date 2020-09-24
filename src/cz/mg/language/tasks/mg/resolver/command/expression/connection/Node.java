package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.tasks.mg.resolver.VariableHelper;
import cz.mg.language.tasks.mg.resolver.contexts.CommandContext;


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
        if(child.getOutputInterface() == null) throw new LanguageException("Cannot connect expressions. Child expression has no output values.");
        if(parent.getInputInterface() == null) throw new LanguageException("Cannot connect expressions. Parent expression has no input values.");
        for(OutputConnector outputConnector : child.getOutputInterface().getConnectors()){
            InputConnector inputConnector = parent.getInputInterface().getRemainingConnectors().getFirst();
            MgLocalVariable connectionVariable = variableHelper.nextExpressionVariable(parent, inputConnector, child, outputConnector);
            Connection.connect(inputConnector, outputConnector, connectionVariable);
        }
    }
}
