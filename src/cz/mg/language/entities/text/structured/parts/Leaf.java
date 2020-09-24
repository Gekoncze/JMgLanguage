package cz.mg.language.entities.text.structured.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.text.structured.Part;


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
