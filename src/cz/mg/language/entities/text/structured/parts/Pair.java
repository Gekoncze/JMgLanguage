package cz.mg.language.entities.text.structured.parts;

import cz.mg.language.entities.text.structured.parts.groups.Colon;


public class Pair extends Part {
    @cz.mg.language.annotations.entity.Part
    private final Part left;

    @cz.mg.language.annotations.entity.Part
    private final Colon right;

    public Pair(Part left, Colon right) {
        this.left = left;
        this.right = right;
    }

    public Part getLeft() {
        return left;
    }

    public Colon getRight() {
        return right;
    }
}
