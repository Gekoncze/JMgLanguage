package cz.mg.language.entities.text.linear.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.linear.tokens.mg.MgSeparator;


public class CSeparatorToken extends MgSeparator {
    public static final CSeparatorToken COLON = new CSeparatorToken(new ReadonlyText(":"));
    public static final CSeparatorToken SEMICOLON = new CSeparatorToken(new ReadonlyText(";"));
    public static final CSeparatorToken COMMA = new CSeparatorToken(new ReadonlyText(","));
    public static final CSeparatorToken DOT = new CSeparatorToken(new ReadonlyText("."));
    public static final CSeparatorToken ARROW = new CSeparatorToken(new ReadonlyText("->"));

    protected CSeparatorToken(ReadableText text) {
        super(text);
    }
}
