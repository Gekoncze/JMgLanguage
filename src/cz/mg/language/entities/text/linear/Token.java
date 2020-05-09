package cz.mg.language.entities.text.linear;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.TextEntity;


public abstract class Token implements TextEntity {
    @Value
    private final ReadableText text;

    public Token(ReadableText text) {
        this.text = text;
    }

    public ReadableText getText() {
        return text;
    }

    public ReadableText toText() {
        return getText();
    }

    @Override
    public String toString() {
        return text.toString();
    }
}
