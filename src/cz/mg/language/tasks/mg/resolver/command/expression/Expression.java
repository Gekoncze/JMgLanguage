package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.*;


public class Expression {
    @Link
    private final MgLogicalExpressionOld logicalExpression;

    @Part
    private final List<Expression> expressions = new List<>();

    @Part
    private final List<MgInstruction> instructions = new List<>();

    @Link
    private final List<MgVariable> output = new List<>();

    @Part
    private InputInterface inputInterface;

    @Link
    private OutputInterface outputInterface;

    public Expression(MgLogicalExpressionOld logicalExpression) {
        this.logicalExpression = logicalExpression;
    }

    public MgLogicalExpressionOld getLogicalExpression() {
        return logicalExpression;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public List<MgInstruction> getInstructions() {
        return instructions;
    }

    public List<MgVariable> getOutput() {
        return output;
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

    public void validate(){
        if(inputInterface == null) throw new RuntimeException();
        if(outputInterface == null) throw new RuntimeException();
        inputInterface.validate();
        outputInterface.validate();
        for(Expression expression : expressions){
            expression.validate();
        }
    }

    public static void connect(Expression parent, Expression child){
        int i = 0;
        for(OutputConnector connector : child.getOutputInterface().getConnectors()){
            Connection.connect(
                parent.getInputInterface().getRemainingConnectors().getFirst(),
                connector,
                child.getOutput().get(i)
            );
            i++;
        }
    }
}
