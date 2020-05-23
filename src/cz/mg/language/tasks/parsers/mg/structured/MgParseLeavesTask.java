package cz.mg.language.tasks.parsers.mg.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.linear.tokens.*;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.*;
import cz.mg.language.tasks.parsers.mg.MgParseTask;


public class MgParseLeavesTask extends MgParseTask {
    @Input
    private final ReadableList<Token> tokens;

    @Output
    private List<Part> parts = null;

    public MgParseLeavesTask(ReadableList<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Part> getParts() {
        return parts;
    }

    @Override
    protected void onRun() {
        parts = new List<>();
        for(Token token : tokens){
            if(token instanceof KeywordToken) parts.addLast(new Keyword(token.getText()));
            else if(token instanceof StampToken) parts.addLast(new Stamp(token.getText()));
            else if(token instanceof NameToken) parts.addLast(new Name(token.getText()));
            else if(token instanceof ValueToken) parts.addLast(new Value(token.getText()));
            else if(token instanceof SymbolToken) parts.addLast(new Symbol(token.getText()));
            else if(token instanceof CommentToken) continue;
            else if(token instanceof WhitespaceToken) continue;
            else throw new LanguageException("Unexpected token of type " + token.getClass().getSimpleName() + ".");
        }
    }
}
