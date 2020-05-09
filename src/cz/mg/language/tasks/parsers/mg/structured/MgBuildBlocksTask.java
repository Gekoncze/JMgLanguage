package cz.mg.language.tasks.parsers.mg.structured;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.common.Line;
import cz.mg.language.entities.text.common.Page;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.common.tokens.mg.Comment;
import cz.mg.language.entities.text.common.tokens.mg.Space;
import cz.mg.language.entities.text.common.tokens.mg.Whitespace;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.parsers.mg.MgParseTask;


public class MgBuildBlocksTask extends MgParseTask {
    private static final int INDENTATION_SIZE = 4;

    @Input
    private final Page page;

    @Output
    private Block root = null;

    public MgBuildBlocksTask(Page page) {
        this.page = page;
    }

    public Block getRoot() {
        return root;
    }

    @Override
    protected void onRun() {
        root = new Block(new List<>());
        List<Line> lines = sweepPage(page);
        for(Line line : lines){
            int lineIndentation = countLineIndentation(line);
            Block targetBlock = getTargetBlock(root, lineIndentation);
            List<Token> tokens = sweepLine(line);
            targetBlock.getBlocks().addLast(new Block(tokens));
        }
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
            if(!(token instanceof Space || token instanceof Comment)) return false;
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
        return token instanceof Whitespace || token instanceof Comment;
    }

    private static int countLineIndentation(Line line){
        int spaces = 0;
        for(Token token : line.getTokens()){
            if(token instanceof Space) spaces++;
            else break;
        }
        if(spaces % INDENTATION_SIZE != 0) throw new LanguageException("Illegal indentation.");
        return spaces / INDENTATION_SIZE;
    }

    private static Block getTargetBlock(Block root, int targetBlockIndentation){
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
