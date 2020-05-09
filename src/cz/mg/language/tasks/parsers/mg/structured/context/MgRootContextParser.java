package cz.mg.language.tasks.parsers.mg.structured.context;

import cz.mg.collections.list.ReadableList;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.structured.blocks.mg.MgRootBlock;


public class MgRootContextParser extends MgContextParser {
    @Override
    public MgRootBlock createBlock(ReadableList<Token> tokens) {
        return new MgRootBlock();
    }

    @Override
    public MgContextParser recognizeBlock(ReadableList<Token> tokens) {
        // TODO
        throw new UnsupportedOperationException();
    }
}
