package cz.mg.language.entities.text.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.text.TextEntity;


public class Block implements TextEntity {
    @Mandatory @cz.mg.language.annotations.storage.Part
    private final List<ReadableText> stamps = new List<>();

    @Mandatory @cz.mg.language.annotations.storage.Part
    private final List<ReadableText> keywords = new List<>();

    @Mandatory @cz.mg.language.annotations.storage.Part
    private final List<Part> parts = new List<>();

    @Mandatory @cz.mg.language.annotations.storage.Part
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
