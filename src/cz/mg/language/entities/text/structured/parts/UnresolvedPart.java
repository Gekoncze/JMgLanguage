package cz.mg.language.entities.text.structured.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class UnresolvedPart extends CommonPart {
    @Value
    private final ReadableText text;

    public UnresolvedPart(ReadableText text) {
        this.text = text;
    }

    public ReadableText getText() {
        return text;
    }
}
