package cz.mg.language.tasks.mg.parser.structured;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.linear.Token;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.parser.MgParseTask;


public class MgParsePartsTask extends MgParseTask {
    @Input
    private final ReadableList<Token> tokens;

    @Output
    private List<Part> parts = null;

    @Subtask
    private MgParseLeavesTask parseLeavesTask = null;

    @Subtask
    private MgParseGroupsTask parseGroupsTask = null;

    public MgParsePartsTask(ReadableList<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Part> getParts() {
        return parts;
    }

    @Override
    protected void onRun() {
        parseLeavesTask = new MgParseLeavesTask(tokens);
        parseLeavesTask.run();
        parts = parseLeavesTask.getParts();

        parseGroupsTask = new MgParseGroupsTask(parts);
        parseGroupsTask.run();
    }
}
