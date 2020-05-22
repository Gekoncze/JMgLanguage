package cz.mg.language.entities.text.linear.tokens.mg;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.Mg;
import cz.mg.language.entities.text.linear.Token;


public class MgWhitespace extends Token implements Mg {
    public MgWhitespace(ReadableText text) {
        super(text);
    }
}
