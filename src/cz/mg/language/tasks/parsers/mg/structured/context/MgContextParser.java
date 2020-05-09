package cz.mg.language.tasks.parsers.mg.structured.context;

import cz.mg.collections.list.ReadableList;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.structured.blocks.mg.MgBlock;


public abstract class MgContextParser {
    public abstract MgBlock createBlock(ReadableList<Token> tokens);
    public abstract MgContextParser recognizeBlock(ReadableList<Token> tokens);
}
