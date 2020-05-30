package cz.mg.language.entities.c.logical.comments;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.c.logical.elements.CElement;


public abstract class CComment extends CElement {
    @Value
    private final ReadableText text;

    protected CComment(ReadableText text) {
        this.text = text;
    }

    public ReadableText getText() {
        return text;
    }
}
