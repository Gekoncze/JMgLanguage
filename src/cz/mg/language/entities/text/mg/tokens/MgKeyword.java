package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Keyword;


public class MgKeyword extends Token implements Keyword {
    public static final MgKeyword APPLICATION = new MgKeyword(new ReadonlyText("APPLICATION"));
    public static final MgKeyword MODULE = new MgKeyword(new ReadonlyText("MODULE"));
    public static final MgKeyword FUNCTION = new MgKeyword(new ReadonlyText("FUNCTION"));
    public static final MgKeyword PRINT = new MgKeyword(new ReadonlyText("PRINT"));

    private MgKeyword(ReadableText text) {
        super(text);
    }

    public static MgKeyword parse(ReadableText text, boolean strict){
        switch (text.toString()){
            case "APPLICATION": return APPLICATION;
            case "MODULE": return MODULE;
            case "FUNCTION": return FUNCTION;
            case "PRINT": return PRINT;
        }

        if(!strict){
            return null;
        } else {
            throw new LanguageException("Unknown keyword '" + text.toString() + "'.");
        }
    }
}
