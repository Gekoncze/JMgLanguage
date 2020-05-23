package cz.mg.language.entities.text.linear.tokens.c;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.linear.Token;


public class CTabToken extends Token {
    public CTabToken() {
        super(new ReadonlyText("\t"));
    }
}
