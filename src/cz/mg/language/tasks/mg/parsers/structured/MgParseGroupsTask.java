package cz.mg.language.tasks.mg.parsers.structured;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.parsers.MgParseTask;
import cz.mg.language.tasks.mg.parsers.structured.composers.*;


public class MgParseGroupsTask extends MgParseTask {
    @Input
    private final List<Part> parts;

    @Subtask
    private MgComposeBracketsTask buildBracketsTask = null;

    @Subtask
    private MgComposeColonsTask buildColonsTask = null;

    @Subtask
    private MgComposePathsTask buildPathsTask = null;

    @Subtask
    private MgComposeDeclarationsTask buildDeclarationsTask = null;

    @Subtask
    private MgComposePairsTask buildPairsTask = null;

    @Subtask
    private MgComposeOperatorsTask buildOperatorsTask = null;

    @Subtask
    private MgComposeListsTask buildListsTask = null;

    public MgParseGroupsTask(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    protected void onRun() {
        List<List<Part>> groups = new List<>();
        groups.addLast(parts);

        buildBracketsTask = new MgComposeBracketsTask(groups);
        buildBracketsTask.run();

        buildColonsTask = new MgComposeColonsTask(groups);
        buildColonsTask.run();

        buildPathsTask = new MgComposePathsTask(groups);
        buildPathsTask.run();

        buildDeclarationsTask = new MgComposeDeclarationsTask(groups);
        buildDeclarationsTask.run();

        buildPairsTask = new MgComposePairsTask(groups);
        buildPairsTask.run();

        buildOperatorsTask = new MgComposeOperatorsTask(groups);
        buildOperatorsTask.run();

        buildListsTask = new MgComposeListsTask(groups);
        buildListsTask.run();
    }
}
