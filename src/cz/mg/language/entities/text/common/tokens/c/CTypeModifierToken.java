package cz.mg.language.entities.text.common.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.Token;


public class CTypeModifierToken extends Token {
    public static final CTypeModifierToken REFERENCE = new CTypeModifierToken(new ReadonlyText("&"));
    public static final CTypeModifierToken POINTER = new CTypeModifierToken(new ReadonlyText("*"));

    protected CTypeModifierToken(ReadableText text) {
        super(text);
    }
}
