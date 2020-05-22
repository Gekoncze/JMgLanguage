package cz.mg.language.entities.logic.mg.parts.expressions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgSymbolExpressionL extends MgExpressionL {
    @Value
    private final ReadableText symbol;

    public MgSymbolExpressionL(ReadableText symbol) {
        this.symbol = symbol;
    }

    public ReadableText getSymbol() {
        return symbol;
    }
}
