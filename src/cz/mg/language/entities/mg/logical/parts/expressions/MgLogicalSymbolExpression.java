package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalSymbolExpression extends MgLogicalExpression {
    @Value
    private ReadableText symbol;

    public MgLogicalSymbolExpression() {
    }

    public MgLogicalSymbolExpression(ReadableText symbol) {
        this.symbol = symbol;
    }

    public ReadableText getSymbol() {
        return symbol;
    }

    public void setSymbol(ReadableText symbol) {
        this.symbol = symbol;
    }
}
