package cz.mg.language.entities.text.common;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.TextEntity;
import cz.mg.language.entities.text.common.tokens.CommonToken;


public class Token extends TextEntity implements CommonToken {
    @Value
    private final ReadableText text;

    public Token(ReadableText text) {
        this.text = text;
    }

    @Override
    public ReadableText getText() {
        return text;
    }

    @Override
    public ReadableText toText() {
        return getText();
    }
}
