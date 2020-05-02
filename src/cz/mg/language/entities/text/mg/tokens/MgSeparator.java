package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.common.tokens.Separator;


public class MgSeparator extends MgSymbol implements Separator {
    public static final MgSeparator COLON = new MgSeparator(new ReadonlyText(":"));
    public static final MgSeparator COMMA = new MgSeparator(new ReadonlyText(","));
    public static final MgSeparator DOT = new MgSeparator(new ReadonlyText("."));

    private MgSeparator(ReadableText text) {
        super(text);
    }

    public static MgSeparator parse(ReadableText text, boolean strict){
        switch (text.toString()){
            case ":": return COLON;
            case ",": return COMMA;
            case ".": return DOT;
        }

        if(!strict){
            return null;
        } else {
            throw new LanguageException("Unknown separator " + text + ".");
        }
    }
}
