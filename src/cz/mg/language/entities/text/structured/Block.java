package cz.mg.language.entities.text.structured;

import cz.mg.collections.list.List;
import cz.mg.language.entities.text.TextEntity;
import cz.mg.language.entities.text.structured.parts.Part;


public abstract class Block<T extends Block> implements TextEntity {
    @cz.mg.language.annotations.entity.Part
    private final List<T> blocks = new List<>();

    @cz.mg.language.annotations.entity.Part
    private final List<Part> parts = new List<>();

    public List<T> getBlocks() {
        return blocks;
    }

    public List<Part> getParts() {
        return parts;
    }
}
