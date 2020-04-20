package cz.mg.compiler.entities.text;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Value;


public class Token extends TextEntity {
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
}
