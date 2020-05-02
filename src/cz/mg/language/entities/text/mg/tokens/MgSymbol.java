package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Symbol;


public abstract class MgSymbol extends Token implements Symbol {
    public MgSymbol(ReadableText text) {
        super(text);
    }
}
