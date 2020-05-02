package cz.mg.language.entities.text.common.tokens.c.preprocessor;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.Token;


public class CDirective extends Token {
    public static final CDirective DEFINE = new CDirective(new ReadonlyText("define"));
    public static final CDirective INCLUDE = new CDirective(new ReadonlyText("include"));
    public static final CDirective IFDEF = new CDirective(new ReadonlyText("ifdef"));
    public static final CDirective IFNDEF = new CDirective(new ReadonlyText("ifndef"));
    public static final CDirective ENDIF = new CDirective(new ReadonlyText("endif"));
    public static final CDirective PRAGMA = new CDirective(new ReadonlyText("pragma"));

    protected CDirective(ReadableText text) {
        super(text);
    }
}
