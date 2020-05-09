package cz.mg.language.entities.text.common.tokens.c;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.tokens.mg.Space;


public class CSpaceToken extends Space {
    public CSpaceToken() {
        super(new ReadonlyText(" "));
    }
}
