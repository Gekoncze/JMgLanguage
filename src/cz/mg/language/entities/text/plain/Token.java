package cz.mg.language.entities.text.plain;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.text.TextEntity;


public class Token implements TextEntity {
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
