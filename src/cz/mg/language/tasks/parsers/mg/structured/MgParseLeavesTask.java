package cz.mg.language.tasks.parsers.mg.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.linear.tokens.KeywordToken;
import cz.mg.language.entities.text.structured.parts.Part;
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
            if(token instanceof KeywordToken) todo;
        }
    }
}
