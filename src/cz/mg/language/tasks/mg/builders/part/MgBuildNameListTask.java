package cz.mg.language.tasks.mg.builders.part;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;
import cz.mg.language.entities.text.structured.parts.leaves.Name;


public class MgBuildNameListTask extends MgBuildPartTask {
    @Output
    private List<ReadableText> list = null;

    public MgBuildNameListTask(Part part) {
        super(part);
    }

    public List<ReadableText> getList() {
        return list;
    }

    @Override
    protected void onRun() {
        list = new List<>();
        if(part instanceof Name){
            list.addLast(((Name) part).getText());
        } else if(part instanceof ListChain){
            ListChain listChain = (ListChain) part;
            for(Part listChainPart : listChain.getParts()){
                if(listChainPart instanceof Name){
                    list.addLast(((Name) listChainPart).getText());
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
