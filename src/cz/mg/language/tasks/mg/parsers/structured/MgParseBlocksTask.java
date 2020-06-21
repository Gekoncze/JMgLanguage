package cz.mg.language.tasks.mg.parsers.structured;

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
import cz.mg.language.tasks.mg.parsers.MgParseTask;


public class MgParseBlocksTask extends MgParseTask {
    private static final int INDENTATION_SIZE = 4;

    @Input
    private final Page page;

    @Output
    private Block root = null;

    @Subtask
    private final List<MgParsePartsTask> parsePartsTasks = new List<>();

    private final StampCollector pendingStamps = new StampCollector();
    private final KeywordCollector pendingKeywords = new KeywordCollector();

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
            splitLineByKeywords(lineItem, indentation);
            line = lineItem.get();
            collectSpaces(line);
            collectStamps(parentBlock, line);
            if(isEmpty(line)) continue;
            collectKeywords(parentBlock, line);
            parsePartsTasks.addLast(new MgParsePartsTask(line.getTokens()));
            parsePartsTasks.getLast().run();
            Block block = new Block();
            block.getStamps().addCollectionLast(pendingStamps.take(parentBlock));
            block.getKeywords().addCollectionLast(pendingKeywords.take(parentBlock));
            block.getParts().addCollectionLast(parsePartsTasks.getLast().getParts());
            parentBlock.getBlocks().addLast(block);
        }

        if(pendingStamps.count() > 0){
            throw new LanguageException("Orphan stamps.");
        }

        if(pendingKeywords.count() > 0){
            throw new LanguageException("Orphan keywords.");
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

    private static void splitLineByKeywords(ListItem<Line> lineItem, int indentation) {
        List<Line> newLines = splitLineByKeywords(lineItem.get());
        lineItem.setData(newLines.removeFirst());
        for(Line newLine : newLines) {
            newLine.getTokens().addCollectionFirst(generateIndentation(indentation + 1));
            lineItem.addNext(newLine);
            lineItem = lineItem.getNextItem();
        }
    }

    private static List<Line> splitLineByKeywords(Line line){
        List<Line> lines = new List<>();
        SplitStatus status = SplitStatus.SPACES;
        Line newLine = new Line();
        for(Token token : line.getTokens()){
            switch (status){
                case SPACES:
                    if(token instanceof KeywordToken){
                        status = SplitStatus.KEYWORDS;
                    } else if(token instanceof SpaceToken){
                        status = SplitStatus.SPACES;
                    } else {
                        status = SplitStatus.PARTS;
                    }
                    break;
                case KEYWORDS:
                    if(token instanceof KeywordToken) {
                        status = SplitStatus.KEYWORDS;
                    } else {
                        status = SplitStatus.PARTS;
                    }
                    break;
                case PARTS:
                    if(token instanceof KeywordToken) {
                        status = SplitStatus.SPACES;
                        lines.addLast(newLine);
                        newLine = new Line();
                    } else {
                        status = SplitStatus.PARTS;
                    }
                    break;
            }
            newLine.getTokens().addLast(token);
        }
        lines.addLast(newLine);
        return lines;
    }

    private enum SplitStatus {
        SPACES,
        KEYWORDS,
        PARTS
    }

    private static List<Token> generateIndentation(int indentation){
        List<Token> tokens = new List<>();
        for(int i = 0; i < indentation; i++){
            for(int ii = 0; ii < INDENTATION_SIZE; ii++){
                tokens.addLast(new SpaceToken(new ReadonlyText(" ")));
            }
        }
        return tokens;
    }

    private void collectSpaces(Line line){
        ListItem<Token> tokenItem = line.getTokens().getFirstItem();
        while(tokenItem != null){
            ListItem<Token> nextItem = tokenItem.getNextItem();
            if(tokenItem.get() instanceof WhitespaceToken) tokenItem.remove();
            tokenItem = nextItem;
        }
    }

    private void collectStamps(Block parentBlock, Line line){
        while(line.getTokens().getFirst() instanceof StampToken){
            pendingStamps.add(parentBlock, (StampToken) line.getTokens().removeFirst());
        }
    }

    private void collectKeywords(Block parentBlock, Line line){
        while(line.getTokens().getFirst() instanceof KeywordToken){
            pendingKeywords.add(parentBlock, (KeywordToken) line.getTokens().removeFirst());
        }
    }

//    public static void main(String[] args) {
//        List<Line> lines = new List<>();
//
//        Line line = new Line(
//            new SpaceToken(new ReadonlyText(" ")),
//            new SpaceToken(new ReadonlyText(" ")),
//            new SpaceToken(new ReadonlyText(" ")),
//            new SpaceToken(new ReadonlyText(" ")),
//            new KeywordToken(new ReadonlyText("FUNCTION")),
//            new SpaceToken(new ReadonlyText(" ")),
//            new NameToken(new ReadonlyText("name")),
//            new SpaceToken(new ReadonlyText(" ")),
//            new KeywordToken(new ReadonlyText("INPUT")),
//            new SpaceToken(new ReadonlyText(" ")),
//            new NameToken(new ReadonlyText("foo")),
//            new SpaceToken(new ReadonlyText(" ")),
//            new KeywordToken(new ReadonlyText("OUTPUT")),
//            new SpaceToken(new ReadonlyText(" ")),
//            new NameToken(new ReadonlyText("bar"))
//        );
//
//        lines.addLast(line);
//
//        System.out.println(line.getTokens().toText("", token -> new ReadonlyText(token.getText())));
//        System.out.println();
//
//        splitLineByKeywords(lines.getFirstItem(), 1);
//        for(Line newLine : lines){
//            System.out.println("{LINE} " + newLine.getTokens().toText("", token -> new ReadonlyText(token.getText())));
//        }
//    }
}
