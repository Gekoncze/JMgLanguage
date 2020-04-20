package cz.mg.compiler.tasks.writers.c.part.expression.call;

import cz.mg.compiler.entities.logic.c.parts.expressions.CDeclaration;
import cz.mg.compiler.entities.logic.c.parts.expressions.CExpression;
import cz.mg.compiler.entities.logic.c.parts.expressions.calls.CFunctionCall;
import cz.mg.compiler.entities.logic.c.parts.expressions.values.CValue;
import cz.mg.compiler.entities.text.tokens.c.CBracket;


public abstract class COperatorWriterTask extends CCallWriterTask {
    protected void writeLeftBracket(CExpression operand){
        if(skip(operand)) return;
        getTokens().addLast(CBracket.ROUND_LEFT);
    }

    protected void writeRightBracket(CExpression operand){
        if(skip(operand)) return;
        getTokens().addLast(CBracket.ROUND_RIGHT);
    }

    private boolean skip(CExpression operand){
        if(operand instanceof CValue) return true;
        if(operand instanceof CFunctionCall) return true;
        if(operand instanceof CDeclaration) return true;
        return false;
    }
}
