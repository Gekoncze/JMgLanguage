package cz.mg.language.entities.text.c.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Bracket;


public class CBracketToken extends Token implements Bracket {
    public static final CBracketToken ROUND_LEFT = new CBracketToken(new ReadonlyText("("));
    public static final CBracketToken ROUND_RIGHT = new CBracketToken(new ReadonlyText(")"));
    public static final CBracketToken SQUARE_LEFT = new CBracketToken(new ReadonlyText("["));
    public static final CBracketToken SQUARE_RIGHT = new CBracketToken(new ReadonlyText("]"));
    public static final CBracketToken CURLY_LEFT = new CBracketToken(new ReadonlyText("{"));
    public static final CBracketToken CURLY_RIGHT = new CBracketToken(new ReadonlyText("}"));
    public static final CBracketToken POINTY_LEFT = new CBracketToken(new ReadonlyText("<"));
    public static final CBracketToken POINTY_RIGHT = new CBracketToken(new ReadonlyText(">"));

    protected CBracketToken(ReadableText text) {
        super(text);
    }
}
