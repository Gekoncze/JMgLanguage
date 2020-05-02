package cz.mg.language.entities.text.c.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Modifier;


public class CTypeModifierToken extends Token implements Modifier {
    public static final CTypeModifierToken REFERENCE = new CTypeModifierToken(new ReadonlyText("&"));
    public static final CTypeModifierToken POINTER = new CTypeModifierToken(new ReadonlyText("*"));

    protected CTypeModifierToken(ReadableText text) {
        super(text);
    }
}
