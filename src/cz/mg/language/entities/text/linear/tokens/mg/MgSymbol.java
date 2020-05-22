package cz.mg.language.entities.text.linear.tokens.mg;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.Mg;
import cz.mg.language.entities.text.linear.Token;


public class MgSymbol extends Token implements Mg {
    public MgSymbol(ReadableText text) {
        super(text);
    }
}
