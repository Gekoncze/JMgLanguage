package cz.mg.language.tasks.parsers.mg.structured.context;

import cz.mg.collections.list.ReadableList;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.structured.blocks.mg.MgRootBlock;


public class MgRootContextParser extends MgContextParser {
    @Override
    public MgRootBlock createBlock(ReadableList<Token> tokens) {
        return new MgRootBlock();
    }

    @Override
    public MgContextParser recognizeBlock(ReadableList<Token> tokens) {
        switch (tokens.getFirst().getText().toString()){
            //case "USING": return new MgUsingContextParser(); todo
            //case "APPLICATION": return new MgApplicationContextParser();
            default: throw new LanguageException("Unknown block " + tokens.getFirst().getText() + " for root context.");
        }
    }
}
