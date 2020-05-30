package cz.mg.language.tasks.mg.parsers.structured.composers;

import cz.mg.collections.list.List;
import cz.mg.collections.list.ListItem;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.structured.parts.Declaration;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Signs;
import cz.mg.language.entities.text.structured.parts.leaves.names.ObjectName;
import cz.mg.language.entities.text.structured.parts.leaves.names.TypeName;


public class MgComposeDeclarationsTask extends MgComposeTask {
    public MgComposeDeclarationsTask(List<List<Part>> groups) {
        super(groups);
    }

    @Override
    protected void onRun(ListItem<Part> partItem) {
        Part part = partItem.get();
        Declaration.Storage storage = getDeclarationStorage(part);
        if(storage != null){
            Part leftPart = partItem.removePrevious();
            Part rightPart = partItem.removeNext();
            if(leftPart == null) throw new LanguageException("Missing left part for declaration.");
            if(rightPart == null) throw new LanguageException("Missing right part for declaration.");
            if(!(leftPart instanceof TypeName)) throw new LanguageException("Unexpected left part for declaration. Expected " + TypeName.class.getSimpleName() + ", got " + leftPart.getClass().getSimpleName() + ".");
            if(!(rightPart instanceof ObjectName)) throw new LanguageException("Unexpected right part for declaration. Expected " + ObjectName.class.getSimpleName() + ", got " + rightPart.getClass().getSimpleName() + ".");
            Declaration declaration = new Declaration(storage, (TypeName)leftPart, (ObjectName)rightPart);
            partItem.setData(declaration);
        }
    }

    private Declaration.Storage getDeclarationStorage(Part part){
        if(part instanceof Signs){
            ReadableText text = ((Signs) part).getText();
            if(text.equals("$")) return Declaration.Storage.VALUE;
            if(text.equals("&")) return Declaration.Storage.ADDRESS;
            if(text.equals("&?")) return Declaration.Storage.NULLABLE_ADDRESS;
        }
        return null;
    }
}
