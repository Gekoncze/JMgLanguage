package cz.mg.language.tasks.mg.parser.structured.composers;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.PathChain;
import cz.mg.language.entities.text.structured.parts.leaves.Special;


public class MgComposePathsTask extends MgComposeTask {
    public MgComposePathsTask(List<List<Part>> groups) {
        super(groups);
    }

    @Override
    protected void onRun(ListItem<Part> partItem) {
        Part part = partItem.get();
        if(isDot(part)){
            PathChain path;
            Part leftPart = partItem.removePrevious();
            Part rightPart = partItem.removeNext();
            if(leftPart == null) throw new LanguageException("Missing left part for path.");
            if(rightPart == null) throw new LanguageException("Missing right part for path.");
            if(leftPart instanceof Special) throw new LanguageException("Unexpected left part (" + leftPart.getClass().getSimpleName() + ") for path.");
            if(rightPart instanceof Special) throw new LanguageException("Unexpected right part (" + leftPart.getClass().getSimpleName() + ") for path.");
            if(leftPart instanceof PathChain){
                path = (PathChain)leftPart;
                path.getParts().addLast(rightPart);
            } else {
                path = new PathChain();
                path.getParts().addLast(leftPart);
                path.getParts().addLast(rightPart);
            }
            partItem.setData(path);
        }
    }

    private boolean isDot(Part part){
        if(part instanceof Special){
            if(((Special) part).getText().equals(".")){
                return true;
            }
        }
        return false;
    }
}
