package cz.mg.language.entities.text.c.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Modifier;


public class CSpecialModifierToken extends Token implements Modifier {
    public CSpecialModifierToken(ReadableText text) {
        super(text);
    }
}
