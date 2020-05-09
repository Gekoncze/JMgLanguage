package cz.mg.language.entities.text.linear.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.linear.tokens.mg.MgBracket;


public class CBracketToken extends MgBracket {
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
