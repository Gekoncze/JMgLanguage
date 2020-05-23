package cz.mg.language.entities.text.linear.tokens.c;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.linear.Token;


public class CSpaceToken extends Token {
    public CSpaceToken() {
        super(new ReadonlyText(" "));
    }
}
