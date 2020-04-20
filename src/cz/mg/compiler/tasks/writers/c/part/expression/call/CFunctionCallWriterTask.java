package cz.mg.compiler.tasks.writers.c.part.expression.call;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.annotations.task.Subtask;
import cz.mg.compiler.entities.logic.c.parts.expressions.CExpression;
import cz.mg.compiler.entities.logic.c.parts.expressions.calls.CFunctionCall;
import cz.mg.compiler.entities.text.Token;
import cz.mg.compiler.entities.text.tokens.c.CBracket;
import cz.mg.compiler.entities.text.tokens.c.CSeparator;
import cz.mg.compiler.entities.text.tokens.common.Identifier;
import cz.mg.compiler.tasks.writers.c.part.expression.CExpressionWriterTask;


public class CFunctionCallWriterTask extends CCallWriterTask {
    @Input
    private final CFunctionCall functionCall;

    @Output
    private final List<Token> tokens = new List<>();

    @Subtask
    private final List<CExpressionWriterTask> expressionWriterTasks = new List<>();

    public CFunctionCallWriterTask(CFunctionCall functionCall) {
        this.functionCall = functionCall;
    }

    @Override
    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    protected void onRun() {
        tokens.addLast(new Identifier(functionCall.getName()));
        tokens.addLast(CBracket.ROUND_LEFT);
        writeInput();
        tokens.addLast(CBracket.ROUND_RIGHT);
    }

    private void writeInput(){
        for(CExpression expression : functionCall.getInput()){
            expressionWriterTasks.addLast(CExpressionWriterTask.create(expression));
            expressionWriterTasks.getLast().run();
            tokens.addCollectionLast(expressionWriterTasks.getLast().getTokens());
            tokens.addLast(CSeparator.COMMA);
        }
        if(tokens.count() > 0) if(tokens.getLast() == CSeparator.COMMA) tokens.removeLast();
    }
}
