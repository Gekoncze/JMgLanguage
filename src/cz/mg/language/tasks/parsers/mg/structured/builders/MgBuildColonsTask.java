package cz.mg.language.tasks.parsers.mg.structured.builders;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Colon;
import cz.mg.language.entities.text.structured.parts.leaves.special.Symbol;


public class MgBuildColonsTask extends MgBuildTask {
    public MgBuildColonsTask(List<List<Part>> groups) {
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
        if(part instanceof Symbol){
            if(((Symbol) part).getText().equals(":")){
                return true;
            }
        }
        return false;
    }
}
