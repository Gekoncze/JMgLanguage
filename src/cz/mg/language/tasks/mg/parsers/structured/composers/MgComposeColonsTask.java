package cz.mg.language.tasks.mg.parsers.structured.composers;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Colon;
import cz.mg.language.entities.text.structured.parts.leaves.Special;


public class MgComposeColonsTask extends MgComposeTask {
    public MgComposeColonsTask(List<List<Part>> groups) {
        super(groups);
    }

    @Override
    protected void onRun(ListItem<Part> partItem) {
        Part part = partItem.get();
        if(isColon(part)){
            Colon colon = new Colon();
            while(partItem.hasNext()){
                colon.getParts().addLast(partItem.removeNext());
            }
            partItem.setData(colon);
            groups.addLast(colon.getParts());
        }
    }

    private boolean isColon(Part part){
        if(part instanceof Special){
            if(((Special) part).getText().equals(":")){
                return true;
            }
        }
        return false;
    }
}
