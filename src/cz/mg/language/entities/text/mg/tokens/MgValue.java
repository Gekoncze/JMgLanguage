package cz.mg.language.entities.text.mg.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.Literal;


public class MgValue extends Token implements Literal {
    public MgValue(ReadableText text) {
        super(text);
    }
}
