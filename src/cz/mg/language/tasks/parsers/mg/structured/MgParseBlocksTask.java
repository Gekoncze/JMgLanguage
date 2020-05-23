package cz.mg.language.tasks.parsers.mg.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.linear.Line;
import cz.mg.language.entities.text.linear.Page;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.linear.tokens.CommentToken;
import cz.mg.language.entities.text.linear.tokens.SpaceToken;
import cz.mg.language.entities.text.linear.tokens.WhitespaceToken;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.parsers.mg.MgParseTask;


public class MgParseBlocksTask extends MgParseTask {
    private static final int INDENTATION_SIZE = 4;

    @Input
    private final Page page;

    @Output
    private Block root = null;

    public MgParseBlocksTask(Page page) {
        this.page = page;
    }

    public Block getRoot() {
        return root;
    }

    @Override
    protected void onRun() {
        root = new Block();

        List<Line> lines = sweepPage(page);
        for(Line line : lines){
            int lineIndentation = countLineIndentation(line);
            Block parentBlock = getParentBlock(root, lineIndentation);
            List<Token> tokens = sweepLine(line);
            MgContextParser parentParser = map.get(parentBlock);
            parentBlock.getBlocks().addLast(createBlock(tokens, parentParser));
        }
    }

    private Block createBlock(ReadableList<Token> tokens, MgContextParser parentParser){
        MgContextParser parser = parentParser.recognizeBlock(tokens);
        Block block = parser.createBlock(tokens);
        map.set(block, parser);
        return block;
    }

    private List<Line> sweepPage(Page page){
        List<Line> lines = new List<>();
        for(Line line : page.getLines()){
            if(!isEmpty(line)){
                lines.addLast(line);
            }
        }
        return lines;
    }

    private boolean isEmpty(Line line){
        for(Token token : line.getTokens()){
            if(!(token instanceof SpaceToken || token instanceof CommentToken)) return false;
        }
        return true;
    }

    private List<Token> sweepLine(Line line){
        List<Token> tokens = new List<>();
        for(Token token : line.getTokens()){
            if(!isEmpty(token)){
                tokens.addLast(token);
            }
        }
        return tokens;
    }

    private boolean isEmpty(Token token){
        return token instanceof WhitespaceToken || token instanceof CommentToken;
    }

    private static int countLineIndentation(Line line){
        int spaces = 0;
        for(Token token : line.getTokens()){
            if(token instanceof SpaceToken) spaces++;
            else break;
        }
        if(spaces % INDENTATION_SIZE != 0) throw new LanguageException("Illegal indentation.");
        return spaces / INDENTATION_SIZE;
    }

    private static Block getParentBlock(Block root, int targetBlockIndentation){
        Block currentBlock = root;
        int currentBlockIndentation = 0;
        while(currentBlock != null){
            if(currentBlockIndentation == targetBlockIndentation){
                return currentBlock;
            } else {
                currentBlock = currentBlock.getBlocks().getLast();
                currentBlockIndentation++;
            }
        }
        throw new LanguageException("Too large indentation.");
    }
}
