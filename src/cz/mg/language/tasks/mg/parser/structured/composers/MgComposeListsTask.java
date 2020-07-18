package cz.mg.language.tasks.mg.parser.structured.composers;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;
import cz.mg.language.entities.text.structured.parts.leaves.Special;


public class MgComposeListsTask extends MgComposeTask {
    public MgComposeListsTask(List<List<Part>> groups) {
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
            if(leftPart instanceof Special) throw new LanguageException("Unexpected left part (" + leftPart.getClass().getSimpleName() + ") for list.");
            if(rightPart instanceof Special) throw new LanguageException("Unexpected right part (" + leftPart.getClass().getSimpleName() + ") for list.");
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
        if(part instanceof Special){
            if(((Special) part).getText().equals(",")){
                return true;
            }
        }
        return false;
    }
}
