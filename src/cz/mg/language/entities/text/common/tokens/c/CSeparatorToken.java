package cz.mg.language.entities.text.common.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.tokens.Separator;


public class CSeparatorToken extends Separator {
    public static final CSeparatorToken COLON = new CSeparatorToken(new ReadonlyText(":"));
    public static final CSeparatorToken SEMICOLON = new CSeparatorToken(new ReadonlyText(";"));
    public static final CSeparatorToken COMMA = new CSeparatorToken(new ReadonlyText(","));
    public static final CSeparatorToken DOT = new CSeparatorToken(new ReadonlyText("."));
    public static final CSeparatorToken ARROW = new CSeparatorToken(new ReadonlyText("->"));

    protected CSeparatorToken(ReadableText text) {
        super(text);
    }
}
