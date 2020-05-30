package cz.mg.language.tasks.builders.mg;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.PathChain;
import cz.mg.language.entities.text.structured.parts.leaves.Name;


public class MgBuildPathTask extends MgBuildPartTask {
    @Output
    private List<ReadableText> path = null;

    public MgBuildPathTask(Part part) {
        super(part);
    }

    public List<ReadableText> getPath() {
        return path;
    }

    @Override
    protected void onRun() {
        path = new List<>();
        if(part instanceof Name){
            path.addLast(((Name) part).getText());
        } else if(part instanceof PathChain){
            PathChain pathChain = (PathChain) part;
            for(Part pathChainPart : pathChain.getParts()){
                if(pathChainPart instanceof Name){
                    path.addLast(((Name) pathChainPart).getText());
                } else {
                    throw new LanguageException("Expected name, but got " + pathChainPart.getClass().getSimpleName() + ".");
                }
            }
        } else if(part == null){
            throw new LanguageException("Missing path.");
        } else {
            throw new LanguageException("Expected name or path chain, but got " + part.getClass().getSimpleName() + ".");
        }
    }
}
