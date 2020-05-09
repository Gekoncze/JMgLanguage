package cz.mg.language.entities.text.linear.tokens.mg;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.MgEntity;
import cz.mg.language.entities.text.linear.Token;


public class MgValue extends Token implements MgEntity {
    public MgValue(ReadableText text) {
        super(text);
    }
}
