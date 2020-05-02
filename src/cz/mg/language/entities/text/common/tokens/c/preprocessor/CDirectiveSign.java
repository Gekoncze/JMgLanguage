package cz.mg.language.entities.text.common.tokens.c.preprocessor;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.Token;


public class CDirectiveSign extends Token {
    public static final CDirectiveSign DIRECTIVE = new CDirectiveSign(new ReadonlyText("#"));
    public static final CDirectiveSign INCLUDE_STANDARD_LEFT = new CDirectiveSign(new ReadonlyText("<"));
    public static final CDirectiveSign INCLUDE_STANDARD_RIGHT = new CDirectiveSign(new ReadonlyText(">"));
    public static final CDirectiveSign INCLUDE_LOCAL_LEFT = new CDirectiveSign(new ReadonlyText("\""));
    public static final CDirectiveSign INCLUDE_LOCAL_RIGHT = new CDirectiveSign(new ReadonlyText("\""));

    protected CDirectiveSign(ReadableText text) {
        super(text);
    }
}
