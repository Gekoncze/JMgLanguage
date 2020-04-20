package cz.mg.compiler.tasks.writers.c.part.expression.call;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.parts.expressions.CExpression;
import cz.mg.compiler.entities.logic.c.parts.expressions.calls.CBinaryOperatorCall;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.c.COperator;
import cz.mg.compiler.tasks.writers.c.part.expression.CExpressionWriterTask;


public class CBinaryOperatorCallWriterTask extends COperatorWriterTask {
    @Input
    private final CBinaryOperatorCall binaryOperatorCall;

    @Output
    private final List<Token> tokens = new List<>();

    @Subtask
    private CExpressionWriterTask leftOperandWriterTask = null;

    @Subtask
    private CExpressionWriterTask rightOperandWriterTask = null;

    public CBinaryOperatorCallWriterTask(CBinaryOperatorCall binaryOperatorCall) {
        this.binaryOperatorCall = binaryOperatorCall;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        writeLeftOperand();
        writeOperator();
        writeRightOperand();
    }

    private void writeOperator() {
        tokens.addLast(COperator.Binary.get(binaryOperatorCall.getOperator()));
    }

    private void writeLeftOperand(){
        CExpression operand = binaryOperatorCall.getLeft();
        writeLeftBracket(operand);
        leftOperandWriterTask = CExpressionWriterTask.create(operand);
        leftOperandWriterTask.run();
        tokens.addCollectionLast(leftOperandWriterTask.getTokens());
        writeRightBracket(operand);
    }

    private void writeRightOperand() {
        CExpression operand = binaryOperatorCall.getRight();
        writeLeftBracket(operand);
        rightOperandWriterTask = CExpressionWriterTask.create(operand);
        rightOperandWriterTask.run();
        tokens.addCollectionLast(rightOperandWriterTask.getTokens());
        writeRightBracket(operand);
    }
}
