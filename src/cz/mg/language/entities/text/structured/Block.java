package cz.mg.language.entities.text.structured;

import cz.mg.collections.list.List;
import cz.mg.language.entities.text.TextEntity;
import cz.mg.language.entities.text.structured.parts.Part;


public abstract class Block implements TextEntity {
    @cz.mg.language.annotations.entity.Part
    private final List<Block> blocks = new List<>();

    @cz.mg.language.annotations.entity.Part
    private final List<Part> parts = new List<>();

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<Part> getParts() {
        return parts;
    }
}
