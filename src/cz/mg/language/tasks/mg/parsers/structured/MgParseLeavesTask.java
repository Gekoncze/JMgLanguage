package cz.mg.language.tasks.mg.parsers.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.linear.tokens.*;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.*;
import cz.mg.language.entities.text.structured.parts.leaves.names.ObjectName;
import cz.mg.language.entities.text.structured.parts.leaves.names.TypeName;
import cz.mg.language.tasks.mg.parsers.MgParseTask;


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
            if(token instanceof TypeNameToken) parts.addLast(new TypeName(token.getText()));
            else if(token instanceof ObjectNameToken) parts.addLast(new ObjectName(token.getText()));
            else if(token instanceof ValueToken) parts.addLast(new Value(token.getText()));
            else if(token instanceof SignsToken) parts.addLast(new Signs(token.getText()));
            else if(token instanceof SpecialToken) parts.addLast(new Special(token.getText()));
            else if(token instanceof CommentToken) continue;
            else if(token instanceof WhitespaceToken) continue;
            else throw new LanguageException("Unexpected token of type " + token.getClass().getSimpleName() + ".");
        }
    }
}
