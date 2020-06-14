package cz.mg.language.tasks.mg.parsers.structured.composers;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Group;
import cz.mg.language.entities.text.structured.parts.leaves.Special;


public class MgComposeGroupsTask extends MgComposeTask {
    public MgComposeGroupsTask(List<List<Part>> groups) {
        super(groups);
    }

    @Override
    protected void onRun(ListItem<Part> partItem) {
        Part part = partItem.get();
        if(isGroupable(part) && isGroupable(partItem.getPrevious())){
            Group group;
            Part leftPart = partItem.removePrevious();
            if(leftPart.getClass() == Group.class){
                group = (Group) leftPart;
                group.getParts().addLast(part);
                partItem.setData(group);
            } else {
                group = new Group();
                group.getParts().addLast(leftPart);
                group.getParts().addLast(part);
                partItem.setData(group);
            }
        }
    }

    private boolean isGroupable(Part part){
        if(part == null) return false;
        return !(part instanceof Special);
    }
}
