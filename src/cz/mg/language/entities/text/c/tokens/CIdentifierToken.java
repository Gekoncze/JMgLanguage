package cz.mg.language.entities.text.c.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Identifier;


public class CIdentifierToken extends Token implements Identifier {
    public CIdentifierToken(ReadableText text) {
        super(text);
    }
}
