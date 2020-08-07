package cz.mg.language.tasks.mg.composer;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.tasks.mg.composer.group.*;


public class MgComposeAllGroupsTask extends MgComposeTask {
    @Input
    private final List<Part> parts;

    @Subtask
    private MgComposeBracketsTask composeBracketsTask = null;

    @Subtask
    private MgComposeColonsTask composeColonsTask = null;

    public MgComposeAllGroupsTask(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    protected void onRun() {
        List<List<Part>> groups = new List<>();
        groups.addLast(parts);

        composeBracketsTask = new MgComposeBracketsTask(groups);
        composeBracketsTask.run();

        composeColonsTask = new MgComposeColonsTask(groups);
        composeColonsTask.run();
    }
}
