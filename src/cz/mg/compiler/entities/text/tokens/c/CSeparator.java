package cz.mg.compiler.entities.text.tokens.c;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.compiler.entities.text.tokens.common.Separator;


public class CSeparator extends Separator {
    public static final CSeparator COLON = new CSeparator(new ReadonlyText(":"));
    public static final CSeparator SEMICOLON = new CSeparator(new ReadonlyText(";"));
    public static final CSeparator COMMA = new CSeparator(new ReadonlyText(","));
    public static final CSeparator DOT = new CSeparator(new ReadonlyText("."));
    public static final CSeparator ARROW = new CSeparator(new ReadonlyText("->"));

    protected CSeparator(ReadableText text) {
        super(text);
    }
}
