package cz.mg.language.entities.text.linear.tokens.mg;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.MgEntity;
import cz.mg.language.entities.text.linear.Token;


public class MgWhitespace extends Token implements MgEntity {
    public MgWhitespace(ReadableText text) {
        super(text);
    }
}
