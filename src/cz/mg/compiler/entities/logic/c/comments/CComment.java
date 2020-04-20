package cz.mg.compiler.entities.logic.c.comments;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.c.elements.CElement;


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
