package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Identifier;


public class MgName extends Token implements Identifier {
    public MgName(ReadableText text) {
        super(text);
    }
}
