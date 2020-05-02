package cz.mg.language.entities.text.c.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Literal;


public class CLiteralToken extends Token implements Literal {
    public CLiteralToken(ReadableText text) {
        super(text);
    }
}
