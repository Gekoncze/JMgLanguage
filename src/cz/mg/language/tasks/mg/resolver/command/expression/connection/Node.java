package cz.mg.language.tasks.mg.resolver.command.expression.connection;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.requirement.Optional;
import cz.mg.language.annotations.task.Utility;
import cz.mg.language.tasks.mg.resolver.VariableHelper;


public class Node {
    @Optional @Utility
    private InputInterface inputInterface;

    @Optional @Utility
    private OutputInterface outputInterface;

    @Optional @Value
    private List<Integer> output;

    public Node() {
    }

    public InputInterface getInputInterface() {
        return inputInterface;
    }

    public void setInputInterface(InputInterface inputInterface) {
        this.inputInterface = inputInterface;
    }

    public OutputInterface getOutputInterface() {
        return outputInterface;
    }

    public void setOutputInterface(OutputInterface outputInterface) {
        this.outputInterface = outputInterface;
    }

    public List<Integer> getOutput() {
        return output;
    }

    public void setOutput(List<Integer> output) {
        this.output = output;
    }

    public static void connect(
        @Mandatory VariableHelper variableHelper,
        @Mandatory Node parent,
        @Mandatory Node child
    ){
        if(child.getOutputInterface() == null) throw new LanguageException("Cannot connect expressions. Child expression has no output values.");
        if(parent.getInputInterface() == null) throw new LanguageException("Cannot connect expressions. Parent expression has no input values.");
        int i = 0;
        for(OutputConnector connector : child.getOutputInterface().getConnectors()){
            Connection.connect(
                parent.getInputInterface().getRemainingConnectors().getFirst(),
                connector,
                variableHelper.getLocalVariable(child.getOutput().get(i))
            );
            i++;
        }
    }
}
