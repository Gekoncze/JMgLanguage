package cz.mg.language.tasks.parsers.mg.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.common.Token;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.UnresolvedPart;
import cz.mg.language.tasks.parsers.mg.MgParseTask;


public class MgBuildPartsTask extends MgParseTask {
    @Input
    private final ReadableList<Token> tokens;

    @Output
    private List<Part> parts = null;

    @Subtask
    private MgBuildStampsTask buildStampsTask = null;

    public MgBuildPartsTask(ReadableList<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Part> getParts() {
        return parts;
    }

    @Override
    protected void onRun() {
        parts = new List<>();
        for(Token token : tokens){
            parts.addLast(new UnresolvedPart(token));
        }

        (buildStampsTask = new MgBuildStampsTask(parts)).run();

    }
}
