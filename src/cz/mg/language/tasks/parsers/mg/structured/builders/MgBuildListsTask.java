package cz.mg.language.tasks.parsers.mg.structured.builders;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;
import cz.mg.language.entities.text.structured.parts.leaves.special.SpecialLeaf;
import cz.mg.language.entities.text.structured.parts.leaves.special.Symbol;


public class MgBuildListsTask extends MgBuildTask {
    public MgBuildListsTask(List<List<Part>> groups) {
        super(groups);
    }

    @Override
    protected void onRun(ListItem<Part> partItem) {
        Part part = partItem.get();
        if(isComma(part)){
            ListChain list;
            Part leftPart = partItem.removePrevious();
            Part rightPart = partItem.removeNext();
            if(leftPart == null) throw new LanguageException("Missing left part for list.");
            if(rightPart == null) throw new LanguageException("Missing right part for list.");
            if(leftPart instanceof SpecialLeaf) throw new LanguageException("Unexpected left part (" + leftPart.getClass().getSimpleName() + ") for list.");
            if(rightPart instanceof SpecialLeaf) throw new LanguageException("Unexpected right part (" + leftPart.getClass().getSimpleName() + ") for list.");
            if(leftPart instanceof ListChain){
                list = (ListChain)leftPart;
                list.getParts().addLast(rightPart);
            } else {
                list = new ListChain();
                list.getParts().addLast(leftPart);
                list.getParts().addLast(rightPart);
            }
            partItem.setData(list);
        }
    }

    private boolean isComma(Part part){
        if(part instanceof Symbol){
            if(((Symbol) part).getText().equals(",")){
                return true;
            }
        }
        return false;
    }
}
