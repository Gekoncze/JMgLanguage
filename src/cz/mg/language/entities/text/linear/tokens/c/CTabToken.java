package cz.mg.language.entities.text.linear.tokens.c;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.linear.tokens.mg.MgWhitespace;


public class CTabToken extends MgWhitespace {
    public CTabToken() {
        super(new ReadonlyText("\t"));
    }
}
