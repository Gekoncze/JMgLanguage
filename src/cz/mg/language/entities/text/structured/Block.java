package cz.mg.language.entities.text.structured;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.structured.parts.Part;


public class Block extends StructuredTextEntity {
    @Link
    private final List<Token> tokens;

    @cz.mg.language.annotations.entity.Part
    private final List<Block> blocks = new List<>();

    @cz.mg.language.annotations.entity.Part
    private final List<Part> parts = new List<>();

    public Block(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<Part> getParts() {
        return parts;
    }
}
