package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Modifier;


public class MgModifier extends MgSymbol implements Modifier {
    public static final MgModifier STAMP = new MgModifier(new ReadonlyText("@"));
    public static final MgModifier ADDRESS = new MgModifier(new ReadonlyText("&"));
    public static final MgModifier VALUE = new MgModifier(new ReadonlyText("$"));
    public static final MgModifier OPTIONAL = new MgModifier(new ReadonlyText("?"));

    private MgModifier(ReadableText text) {
        super(text);
    }

    public static MgModifier parse(ReadableText text, boolean strict) {
        switch (text.toString()){
            case "@": return STAMP;
            case "&": return ADDRESS;
            case "$": return VALUE;
            case "?": return OPTIONAL;
        }

        if(!strict){
            return null;
        } else {
            throw new LanguageException("Unknown modifier " + text + ".");
        }
    }
}
