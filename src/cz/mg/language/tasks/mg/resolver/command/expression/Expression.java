package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.InputInterface;
import cz.mg.language.tasks.mg.resolver.command.expression.connection.OutputInterface;


public class Expression {
    @Link
    private final MgLogicalExpression logicalExpression;

    @Part
    private final List<Expression> expressions = new List<>();

    @Part
    private final List<MgInstruction> instructions = new List<>();

    @Part
    private final List<MgVariable> declaredVariables = new List<>();

    @Part
    private InputInterface inputInterface;

    @Link
    private OutputInterface outputInterface;

    public Expression(MgLogicalExpression logicalExpression) {
        this.logicalExpression = logicalExpression;
    }

    public MgLogicalExpression getLogicalExpression() {
        return logicalExpression;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public List<MgInstruction> getInstructions() {
        return instructions;
    }

    public List<MgVariable> getDeclaredVariables() {
        return declaredVariables;
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
    }
}
