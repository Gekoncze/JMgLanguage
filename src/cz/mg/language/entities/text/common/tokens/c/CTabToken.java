package cz.mg.language.entities.text.common.tokens.c;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.common.tokens.mg.Whitespace;


public class CTabToken extends Whitespace {
    public CTabToken() {
        super(new ReadonlyText("\t"));
    }
}
