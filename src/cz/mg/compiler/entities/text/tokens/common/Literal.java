package cz.mg.compiler.entities.text.tokens.common;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.entities.text.Token;


public class Literal extends Token {
    public Literal(ReadableText text) {
        super(text);
    }
}
