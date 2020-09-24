package cz.mg.language.entities.text.structured.parts;

import cz.mg.collections.list.List;
import cz.mg.language.entities.text.structured.Part;


public abstract class Clump extends Part {
    @cz.mg.language.annotations.storage.Part
    protected final List<Part> parts = new List<>();

    public Clump() {
    }

    public List<Part> getParts() {
        return parts;
    }
}
