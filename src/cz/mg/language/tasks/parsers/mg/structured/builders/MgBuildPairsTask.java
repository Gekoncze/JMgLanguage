package cz.mg.language.tasks.parsers.mg.structured.builders;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.structured.parts.Pair;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Colon;
import cz.mg.language.entities.text.structured.parts.leaves.special.SpecialLeaf;


public class MgBuildPairsTask extends MgBuildTask {
    public MgBuildPairsTask(List<List<Part>> groups) {
        super(groups);
    }

    @Override
    protected void onRun(ListItem<Part> partItem) {
        Part part = partItem.get();
        if(part instanceof Colon){
            Part leftPart = partItem.removePrevious();
            if(leftPart instanceof SpecialLeaf) throw new LanguageException("Unexpected left part (" + leftPart.getClass().getSimpleName() + ") of colon.");
            Pair pair = new Pair(leftPart, (Colon) part);
            partItem.setData(pair);
        }
    }
}
