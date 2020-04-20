package cz.mg.compiler.entities.text.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.entities.text.tokens.common.Keyword;


public class CKeyword extends Keyword {
    public static final CKeyword TYPEDEF = new CKeyword(new ReadonlyText("typedef"));
    public static final CKeyword STRUCT = new CKeyword(new ReadonlyText("struct"));
    public static final CKeyword VOID = new CKeyword(new ReadonlyText("void"));
    public static final CKeyword BREAK = new CKeyword(new ReadonlyText("break"));
    public static final CKeyword CONTINUE = new CKeyword(new ReadonlyText("continue"));
    public static final CKeyword IF = new CKeyword(new ReadonlyText("if"));
    public static final CKeyword ELSE = new CKeyword(new ReadonlyText("else"));
    public static final CKeyword FOR = new CKeyword(new ReadonlyText("for"));
    public static final CKeyword WHILE = new CKeyword(new ReadonlyText("while"));
    public static final CKeyword RETURN = new CKeyword(new ReadonlyText("return"));

    protected CKeyword(ReadableText text) {
        super(text);
    }
}
