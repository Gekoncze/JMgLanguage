package cz.mg.language.tasks.parsers.mg.structured;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.parsers.mg.MgParseTask;
import cz.mg.language.tasks.parsers.mg.structured.builders.*;


public class MgParseGroupsTask extends MgParseTask {
    @Input
    private final List<Part> parts;

    @Subtask
    private MgBuildBracketsTask buildBracketsTask = null;

    @Subtask
    private MgBuildColonsTask buildColonsTask = null;

    @Subtask
    private MgBuildPathsTask buildPathsTask = null;

    @Subtask
    private MgBuildDeclarationsTask buildDeclarationsTask = null;

    @Subtask
    private MgBuildPairsTask buildPairsTask = null;

    @Subtask
    private MgBuildOperatorsTask buildOperatorsTask = null;

    @Subtask
    private MgBuildListsTask buildListsTask = null;

    public MgParseGroupsTask(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    protected void onRun() {
        List<List<Part>> groups = new List<>();
        groups.addLast(parts);

        buildBracketsTask = new MgBuildBracketsTask(groups);
        buildBracketsTask.run();

        buildColonsTask = new MgBuildColonsTask(groups);
        buildColonsTask.run();

        buildPathsTask = new MgBuildPathsTask(groups);
        buildPathsTask.run();

        buildDeclarationsTask = new MgBuildDeclarationsTask(groups);
        buildDeclarationsTask.run();

        buildPairsTask = new MgBuildPairsTask(groups);
        buildPairsTask.run();

        buildOperatorsTask = new MgBuildOperatorsTask(groups);
        buildOperatorsTask.run();

        buildListsTask = new MgBuildListsTask(groups);
        buildListsTask.run();
    }
}
