package cz.mg.language.entities.text.linear.tokens.c;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.linear.tokens.mg.MgSpace;


public class CSpaceToken extends MgSpace {
    public CSpaceToken() {
        super(new ReadonlyText(" "));
    }
}
