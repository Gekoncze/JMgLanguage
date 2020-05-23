package cz.mg.language.entities.text.linear.tokens;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.linear.Token;


public class WhitespaceToken extends Token {
    public WhitespaceToken(ReadableText text) {
        super(text);
    }
}
