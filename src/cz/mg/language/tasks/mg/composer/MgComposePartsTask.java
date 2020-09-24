package cz.mg.language.tasks.mg.composer;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.plain.Token;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.Task;


public class MgComposePartsTask extends Task {
    @Input
    private final ReadableList<Token> tokens;

    @Output
    private List<Part> parts = null;

    public MgComposePartsTask(ReadableList<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Part> getParts() {
        return parts;
    }

    @Override
    protected void onRun() {
        MgComposeAllLeavesTask composeLeavesTask = new MgComposeAllLeavesTask(tokens);
        composeLeavesTask.run();
        parts = composeLeavesTask.getParts();

        MgComposeAllGroupsTask composeGroupsTask = new MgComposeAllGroupsTask(parts);
        composeGroupsTask.run();
    }
}
