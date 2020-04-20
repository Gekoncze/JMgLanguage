package cz.mg.compiler.entities.text.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.entities.text.Token;


public class CTypeModifier extends Token {
    public static final CTypeModifier REFERENCE = new CTypeModifier(new ReadonlyText("&"));
    public static final CTypeModifier POINTER = new CTypeModifier(new ReadonlyText("*"));

    protected CTypeModifier(ReadableText text) {
        super(text);
    }
}
