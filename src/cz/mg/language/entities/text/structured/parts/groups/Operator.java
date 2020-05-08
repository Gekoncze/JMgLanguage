package cz.mg.language.entities.text.structured.parts.groups;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.structured.parts.Part;


public class Operator extends Group {
    @Value
    private final ReadableText text;

    public Operator(ReadableText text) {
        this(text, null, null);
    }

    public Operator(ReadableText text, Part left, Part right) {
        this.text = text;
        getParts().addFirst(left);
        getParts().addLast(right);
    }

    public ReadableText getText() {
        return text;
    }

    public Part getLeft() {
        return getParts().getFirst();
    }

    public void setLeft(Part left) {
        getParts().getFirstItem().setData(left);
    }

    public Part getRight() {
        return getParts().getLast();
    }

    public void setRight(Part right) {
        getParts().getLastItem().setData(right);
    }
}
