package cz.mg.language.entities.text.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.TextEntity;
import cz.mg.language.entities.text.structured.parts.Part;


public class Block implements TextEntity {
    @cz.mg.language.annotations.entity.Part
    private final List<ReadableText> stamps = new List<>();

    @cz.mg.language.annotations.entity.Part
    private final List<ReadableText> keywords = new List<>();

    @cz.mg.language.annotations.entity.Part
    private final List<Part> parts = new List<>();

    @cz.mg.language.annotations.entity.Part
    private final List<Block> blocks = new List<>();

    public Block() {
    }

    public List<ReadableText> getStamps() {
        return stamps;
    }

    public List<ReadableText> getKeywords() {
        return keywords;
    }

    public List<Part> getParts() {
        return parts;
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
