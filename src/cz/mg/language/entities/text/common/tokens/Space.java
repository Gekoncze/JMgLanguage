package cz.mg.language.entities.text.common.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.Token;


public class Space extends Token implements CommonToken {
    public static final Space SPACE = new Space(1);
    public static final Space INDENT = new Space(4);
    //public static final Space NEWLINE = new Space(new ReadonlyText("\n"));

    public Space(ReadableText text) {
        super(text);
    }

    public Space(int count) {
        super(new ReadonlyText(" ").repeat(count));
    }
}
