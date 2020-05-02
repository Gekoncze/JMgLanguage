package cz.mg.language.entities.text.common;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class Token extends CommonTextEntity {
    @Value
    private final ReadableText text;

    public Token(ReadableText text) {
        this.text = text;
    }

    public ReadableText getText() {
        return text;
    }

    @Override
    public ReadableText toText() {
        return getText();
    }

    @Override
    public String toString() {
        return text.toString();
    }
}
