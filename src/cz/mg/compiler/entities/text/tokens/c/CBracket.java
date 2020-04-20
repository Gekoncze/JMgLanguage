package cz.mg.compiler.entities.text.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.entities.text.tokens.common.Bracket;


public class CBracket extends Bracket {
    public static final CBracket ROUND_LEFT = new CBracket(new ReadonlyText("("));
    public static final CBracket ROUND_RIGHT = new CBracket(new ReadonlyText(")"));
    public static final CBracket SQUARE_LEFT = new CBracket(new ReadonlyText("["));
    public static final CBracket SQUARE_RIGHT = new CBracket(new ReadonlyText("]"));
    public static final CBracket CURLY_LEFT = new CBracket(new ReadonlyText("{"));
    public static final CBracket CURLY_RIGHT = new CBracket(new ReadonlyText("}"));
    public static final CBracket POINTY_LEFT = new CBracket(new ReadonlyText("<"));
    public static final CBracket POINTY_RIGHT = new CBracket(new ReadonlyText(">"));

    protected CBracket(ReadableText text) {
        super(text);
    }
}
