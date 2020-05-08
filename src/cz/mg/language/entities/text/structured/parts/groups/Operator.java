package cz.mg.language.entities.text.structured.parts.groups;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.structured.parts.CommonPart;


public class Operator extends Group {
    @Value
    private final ReadableText text;

    public Operator(ReadableText text) {
        this(text, null, null);
    }

    public Operator(ReadableText text, CommonPart left, CommonPart right) {
        this.text = text;
        getParts().addFirst(left);
        getParts().addLast(right);
    }

    public ReadableText getText() {
        return text;
    }

    public CommonPart getLeft() {
        return getParts().getFirst();
    }

    public void setLeft(CommonPart left) {
        getParts().getFirstItem().setData(left);
    }

    public CommonPart getRight() {
        return getParts().getLast();
    }

    public void setRight(CommonPart right) {
        getParts().getLastItem().setData(right);
    }
}
