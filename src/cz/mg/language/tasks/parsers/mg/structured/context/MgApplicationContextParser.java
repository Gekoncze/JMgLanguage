//package cz.mg.language.tasks.parsers.mg.structured.context;
//
//import cz.mg.collections.list.ReadableList;
//import cz.mg.language.entities.text.linear.Token;
//import cz.mg.language.entities.text.structured.blocks.mg.MgApplicationBlock;
//import cz.mg.language.entities.text.structured.blocks.mg.MgBlock;
//
//
//public class MgApplicationContextParser extends MgContextParser {
//    @Override
//    public MgBlock createBlock(ReadableList<Token> tokens) {
//        return new MgApplicationBlock(parseParts(tokens));
//    }
//
//    @Override
//    public MgContextParser recognizeBlock(ReadableList<Token> tokens) {
//        //todo;
//        throw new UnsupportedOperationException();
//    }
//}
