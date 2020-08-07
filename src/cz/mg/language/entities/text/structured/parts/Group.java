package cz.mg.language.entities.text.structured.parts;

import cz.mg.collections.list.List;
import cz.mg.language.entities.text.structured.Part;


public abstract class Group extends Part {
    @cz.mg.language.annotations.entity.Part
    protected final List<Part> parts = new List<>();

    public Group() {
    }

    public List<Part> getParts() {
        return parts;
    }
}
