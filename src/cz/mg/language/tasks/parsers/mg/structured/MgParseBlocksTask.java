package cz.mg.language.tasks.parsers.mg.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.collections.map.Map;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.linear.Line;
import cz.mg.language.entities.text.linear.Page;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.linear.tokens.mg.MgComment;
import cz.mg.language.entities.text.linear.tokens.mg.MgSpace;
import cz.mg.language.entities.text.linear.tokens.mg.MgWhitespace;
import cz.mg.language.entities.text.structured.blocks.mg.MgBlock;
import cz.mg.language.entities.text.structured.blocks.mg.MgRootBlock;
import cz.mg.language.tasks.parsers.mg.MgParseTask;
import cz.mg.language.tasks.parsers.mg.structured.context.MgContextParser;
import cz.mg.language.tasks.parsers.mg.structured.context.MgRootContextParser;


public class MgParseBlocksTask extends MgParseTask {
    private static final int INDENTATION_SIZE = 4;

    @Input
    private final Page page;

    @Output
    private MgRootBlock root = null;

    private Map<MgBlock, MgContextParser> map = new Map<>();

    public MgParseBlocksTask(Page page) {
        this.page = page;
    }

    public MgRootBlock getRoot() {
        return root;
    }

    @Override
    protected void onRun() {
        root = new MgRootBlock();
        map.set(root, new MgRootContextParser());

        List<Line> lines = sweepPage(page);
        for(Line line : lines){
            int lineIndentation = countLineIndentation(line);
            MgBlock parentBlock = getParentBlock(root, lineIndentation);
            List<Token> tokens = sweepLine(line);
            MgContextParser parentParser = map.get(parentBlock);
            parentBlock.getBlocks().addLast(createBlock(tokens, parentParser));
        }
    }

    private MgBlock createBlock(ReadableList<Token> tokens, MgContextParser parentParser){
        MgContextParser parser = parentParser.recognizeBlock(tokens);
        MgBlock block = parser.createBlock(tokens);
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
            if(!(token instanceof MgSpace || token instanceof MgComment)) return false;
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
        return token instanceof MgWhitespace || token instanceof MgComment;
    }

    private static int countLineIndentation(Line line){
        int spaces = 0;
        for(Token token : line.getTokens()){
            if(token instanceof MgSpace) spaces++;
            else break;
        }
        if(spaces % INDENTATION_SIZE != 0) throw new LanguageException("Illegal indentation.");
        return spaces / INDENTATION_SIZE;
    }

    private static MgBlock getParentBlock(MgBlock root, int targetBlockIndentation){
        MgBlock currentBlock = root;
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
