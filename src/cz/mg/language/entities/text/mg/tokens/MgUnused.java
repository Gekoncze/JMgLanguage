package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.Token;


public class MgUnused extends Token {
    public static final MgUnused U1 = new MgUnused(new ReadonlyText("~"));
    public static final MgUnused U2 = new MgUnused(new ReadonlyText("°"));
    public static final MgUnused U3 = new MgUnused(new ReadonlyText(";"));
    public static final MgUnused U4 = new MgUnused(new ReadonlyText("\'"));
    public static final MgUnused U5 = new MgUnused(new ReadonlyText("|"));

    private MgUnused(ReadableText text) {
        super(text);
    }
}
