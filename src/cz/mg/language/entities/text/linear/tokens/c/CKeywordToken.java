package cz.mg.language.entities.text.linear.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.linear.tokens.mg.MgKeyword;


public class CKeywordToken extends MgKeyword {
    public static final CKeywordToken TYPEDEF = new CKeywordToken(new ReadonlyText("typedef"));
    public static final CKeywordToken STRUCT = new CKeywordToken(new ReadonlyText("struct"));
    public static final CKeywordToken VOID = new CKeywordToken(new ReadonlyText("void"));
    public static final CKeywordToken BREAK = new CKeywordToken(new ReadonlyText("break"));
    public static final CKeywordToken CONTINUE = new CKeywordToken(new ReadonlyText("continue"));
    public static final CKeywordToken IF = new CKeywordToken(new ReadonlyText("if"));
    public static final CKeywordToken ELSE = new CKeywordToken(new ReadonlyText("else"));
    public static final CKeywordToken FOR = new CKeywordToken(new ReadonlyText("for"));
    public static final CKeywordToken WHILE = new CKeywordToken(new ReadonlyText("while"));
    public static final CKeywordToken RETURN = new CKeywordToken(new ReadonlyText("return"));

    protected CKeywordToken(ReadableText text) {
        super(text);
    }
}
