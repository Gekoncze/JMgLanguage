package cz.mg.compiler.tasks.writers.c.part.expression.call;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.parts.expressions.CExpression;
import cz.mg.compiler.entities.logic.c.parts.expressions.calls.CUnaryRightOperatorCall;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.c.COperator;
import cz.mg.compiler.tasks.writers.c.part.expression.CExpressionWriterTask;


public class CUnaryRightOperatorCallWriterTask extends COperatorWriterTask {
    @Input
    private final CUnaryRightOperatorCall unaryRightOperatorCall;

    @Output
    private final List<Token> tokens = new List<>();

    @Subtask
    private CExpressionWriterTask operandWriterTask = null;

    public CUnaryRightOperatorCallWriterTask(CUnaryRightOperatorCall unaryRightOperatorCall) {
        this.unaryRightOperatorCall = unaryRightOperatorCall;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        writeOperand();
        writeOperator();
    }

    private void writeOperator(){
        tokens.addLast(COperator.Unary.get(unaryRightOperatorCall.getOperator()));
    }

    private void writeOperand(){
        CExpression operand = unaryRightOperatorCall.getRight();
        writeLeftBracket(operand);
        operandWriterTask = CExpressionWriterTask.create(operand);
        operandWriterTask.run();
        tokens.addCollectionLast(operandWriterTask.getTokens());
        writeRightBracket(operand);
    }
}
