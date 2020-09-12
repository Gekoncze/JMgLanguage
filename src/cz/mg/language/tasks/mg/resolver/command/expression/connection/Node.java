package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.VariableHelper;


public abstract class Node {
    @Mandatory @Part
    private final InputInterface inputInterface;

    @Mandatory @Part
    private final OutputInterface outputInterface;

    @Mandatory @Part
    private final List<Node> input = new List<>();

    public Node(
        @Mandatory InputInterface inputInterface,
        @Mandatory OutputInterface outputInterface
    ) {
        this.inputInterface = inputInterface;
        this.outputInterface = outputInterface;
    }

    public abstract MgExpression createExpression();

    public InputInterface getInputInterface() {
        return inputInterface;
    }

    public OutputInterface getOutputInterface() {
        return outputInterface;
    }

    public List<Node> getInput() {
        return input;
    }

    public static void connect(
        @Mandatory VariableHelper variableHelper,
        @Mandatory Node parent,
        @Mandatory Node child
    ){
        if(child.getOutputInterface() == null) throw new LanguageException("Cannot connect expressions. Child expression has no output values.");
        if(parent.getInputInterface() == null) throw new LanguageException("Cannot connect expressions. Parent expression has no input values.");
        for(OutputConnector outputConnector : child.getOutputInterface().getConnectors()){
            InputConnector inputConnector = parent.getInputInterface().getRemainingConnectors().getFirst();
            MgLocalVariable connectionVariable = variableHelper.nextExpressionVariable(parent, inputConnector, child, outputConnector);
            Connection.connect(inputConnector, outputConnector, connectionVariable);
        }
    }
}
