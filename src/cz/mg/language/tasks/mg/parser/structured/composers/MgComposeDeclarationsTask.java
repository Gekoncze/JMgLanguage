package cz.mg.language.tasks.mg.parser.structured.composers;

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
        Modifier modifier = getDeclarationStorage(part);
        if(modifier != null){
            Part leftPart = partItem.removePrevious();
            Part rightPart = partItem.removeNext();
            if(leftPart == null) throw new LanguageException("Missing left part for declaration.");
            if(rightPart == null) throw new LanguageException("Missing right part for declaration.");
            if(!(leftPart instanceof TypeName)) throw new LanguageException("Unexpected left part for declaration. Expected " + TypeName.class.getSimpleName() + ", got " + leftPart.getClass().getSimpleName() + ".");
            if(!(rightPart instanceof ObjectName)) throw new LanguageException("Unexpected right part for declaration. Expected " + ObjectName.class.getSimpleName() + ", got " + rightPart.getClass().getSimpleName() + ".");
            Declaration declaration = new Declaration(modifier.storage, modifier.requirement, (TypeName)leftPart, (ObjectName)rightPart);
            partItem.setData(declaration);
        }
    }

    private Modifier getDeclarationStorage(Part part){
        if(part instanceof Signs){
            ReadableText text = ((Signs) part).getText();
            if(text.equals("$")) return new Modifier(Declaration.Storage.DIRECT, Declaration.Requirement.MANDATORY);
            if(text.equals("$?")) return new Modifier(Declaration.Storage.DIRECT, Declaration.Requirement.OPTIONAL);
            if(text.equals("&")) return new Modifier(Declaration.Storage.INDIRECT, Declaration.Requirement.MANDATORY);
            if(text.equals("&?")) return new Modifier(Declaration.Storage.INDIRECT, Declaration.Requirement.OPTIONAL);
        }
        return null;
    }

    private static class Modifier {
        public Declaration.Storage storage;
        public Declaration.Requirement requirement;

        public Modifier(Declaration.Storage storage, Declaration.Requirement requirement) {
            this.storage = storage;
            this.requirement = requirement;
        }
    }
}
