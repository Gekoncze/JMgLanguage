package cz.mg.language.tasks.mg.parser.structured.composers;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.parser.MgParseTask;


public abstract class MgComposeTask extends MgParseTask {
    @Input
    protected final List<List<Part>> groups;

    public MgComposeTask(List<List<Part>> groups) {
        this.groups = groups;
    }

    @Override
    protected void onRun() {
        for(ListItem<List<Part>> groupItem = groups.getFirstItem(); groupItem != null; groupItem = groupItem.getNextItem()){
            List<Part> group = groupItem.get();
            onRun(group);
        }
    }

    protected void onRun(List<Part> group) {
        for(ListItem<Part> partItem = group.getFirstItem(); partItem != null; partItem = partItem.getNextItem()){
            onRun(partItem);
        }
    }

    protected abstract void onRun(ListItem<Part> partItem);
}
