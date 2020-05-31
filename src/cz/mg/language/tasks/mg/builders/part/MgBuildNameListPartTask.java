package cz.mg.language.tasks.mg.builders.part;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;
import cz.mg.language.entities.text.structured.parts.leaves.Name;


public class MgBuildNameListPartTask extends MgBuildPartTask {
    @Output
    private final List<ReadableText> names = new List<>();

    public MgBuildNameListPartTask(Part part) {
        super(part);
    }

    public List<ReadableText> getNames() {
        return names;
    }

    @Override
    protected void onRun() {
        if(part instanceof Name){
            names.addLast(((Name) part).getText());
        } else if(part instanceof ListChain){
            ListChain listChain = (ListChain) part;
            for(Part listChainPart : listChain.getParts()){
                if(listChainPart instanceof Name){
                    names.addLast(((Name) listChainPart).getText());
                } else {
                    throw new LanguageException("Expected name, but got " + listChainPart.getClass().getSimpleName() + ".");
                }
            }
        } else if(part == null){
            throw new LanguageException("Missing list.");
        } else {
            throw new LanguageException("Expected name or list chain, but got " + part.getClass().getSimpleName() + ".");
        }
    }
}
