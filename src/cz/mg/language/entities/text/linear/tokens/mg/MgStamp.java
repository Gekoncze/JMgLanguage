package cz.mg.language.entities.text.linear.tokens.mg;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.Mg;
import cz.mg.language.entities.text.linear.Token;


public class MgStamp extends Token implements Mg {
    public MgStamp(ReadableText text) {
        super(text);
    }
}
