package cz.mg.language.entities.text.structured.parts.leaves;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.structured.parts.Part;


public abstract class Leaf extends Part {
    @Value
    private final ReadableText text;

    public Leaf(ReadableText text) {
        this.text = text;
    }

    public ReadableText getText() {
        return text;
    }
}
