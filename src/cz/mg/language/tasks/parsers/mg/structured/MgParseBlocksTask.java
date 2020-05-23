package cz.mg.language.tasks.parsers.mg.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.linear.Line;
import cz.mg.language.entities.text.linear.Page;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.linear.tokens.*;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.tasks.parsers.mg.MgParseTask;


public class MgParseBlocksTask extends MgParseTask {
    private static final int INDENTATION_SIZE = 4;

    @Input
    private final Page page;

    @Output
    private Block root = null;

    @Subtask
    private final List<MgParsePartsTask> parsePartsTasks = new List<>();

    public MgParseBlocksTask(Page page) {
        this.page = page;
    }

    public Block getRoot() {
        return root;
    }

    @Override
    protected void onRun() {
        root = new Block();

        for(ListItem<Line> lineItem = page.getLines().getFirstItem(); lineItem != null; lineItem = lineItem.getNextItem()){
            Line line = lineItem.get();
            if(isEmpty(line)) continue;
            int indentation = countIndentation(line);
            Block parentBlock = getParentBlock(root, indentation);
            flattenLineByStamps(lineItem, indentation);
            flattenLineByKeywords(lineItem, indentation);
            parsePartsTasks.addLast(new MgParsePartsTask(line.getTokens()));
            parsePartsTasks.getLast().run();
            parentBlock.getBlocks().addLast(new Block(parsePartsTasks.getLast().getParts()));
        }
    }

    private boolean isEmpty(Line line){
        for(Token token : line.getTokens()){
            if(!isEmpty(token)){
                return false;
            }
        }
        return true;
    }

    private boolean isEmpty(Token token){
        return token instanceof WhitespaceToken || token instanceof CommentToken;
    }

    private static int countIndentation(Line line){
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

    private void flattenLineByStamps(ListItem<Line> lineItem, int indentation) {
        Line line = lineItem.get();
        boolean stamps = false;
        for(ListItem<Token> tokenItem = line.getTokens().getFirstItem(); tokenItem != null; tokenItem = tokenItem.getNextItem()){
            Token token = tokenItem.get();
            if(token instanceof WhitespaceToken) continue;
            else if(token instanceof StampToken) stamps = true;
            else {
                if(stamps){
                    splitLine(lineItem, tokenItem, indentation);
                    return;
                }
            }
        }
    }

    private void flattenLineByKeywords(ListItem<Line> lineItem, int indentation) {
        Line line = lineItem.get();
        boolean nonKeywords = false;
        for(ListItem<Token> tokenItem = line.getTokens().getFirstItem(); tokenItem != null; tokenItem = tokenItem.getNextItem()) {
            Token token = tokenItem.get();
            if(token instanceof WhitespaceToken) continue;
            else if(!(token instanceof KeywordToken)) nonKeywords = true;
            else {
                if(nonKeywords){
                    splitLine(lineItem, tokenItem, indentation);
                    return;
                }
            }
        }
    }

    private void splitLine(ListItem<Line> lineItem, ListItem<Token> tokenItem, int indentation){
        Line newLine = new Line();
        while(tokenItem.hasNext()){
            newLine.getTokens().addLast(tokenItem.removeNext());
        }
        newLine.getTokens().addFirst(tokenItem.remove());
        newLine.getTokens().addCollectionFirst(generateIndentation(indentation));
        lineItem.addNext(newLine);
    }

    private List<Token> generateIndentation(int indentation){
        List<Token> tokens = new List<>();
        for(int i = 0; i < indentation; i++){
            for(int ii = 0; ii < INDENTATION_SIZE; i++){
                tokens.addLast(new SpaceToken(new ReadonlyText(" ")));
            }
        }
        return tokens;
    }
}
