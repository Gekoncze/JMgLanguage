package cz.mg.language.tasks.mg.resolver.command.expression;

import cz.mg.collections.array.ArrayView;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.mg.runtime.components.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public class Expression {
    @Link
    private final MgLogicalExpression logicalExpression;

    @Part
    private final List<Expression> expressions = new List<>();

    @Part
    private final List<MgInstruction> instructions = new List<>();

    @Part
    private final List<MgVariable> declaredVariables = new List<>();

    @Link
    private ReadableArray<MgVariable> input;

    @Link
    private ReadableArray<MgVariable> output;

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

    public ReadableArray<MgVariable> getInput() {
        return input;
    }

    public void setInput(ReadableArray<MgVariable> input) {
        this.input = input;
    }

    public ReadableArray<MgVariable> getOutput() {
        return output;
    }

    public void setOutput(ReadableArray<MgVariable> output) {
        this.output = output;
    }

    public ReadableArray<MgVariable> getRemainingInput(){
        int offset = 0;
        for(Expression expression : expressions){
            offset += expression.getOutput().count();
        }
        return new ArrayView<>(input, offset, input.count());
    }
}
