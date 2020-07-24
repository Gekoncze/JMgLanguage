package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Leaf;
import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.entities.text.structured.parts.leaves.Signs;


public class MgBuildOperatorTask extends MgBuildPartTask<Leaf> {
    @Output
    private ReadableText operator;

    public MgBuildOperatorTask(Part part) {
        super(part, Leaf.class);
    }

    public ReadableText getOperator() {
        return operator;
    }

    @Override
    protected void buildPart(Leaf part) {
        if(part instanceof Name || part instanceof Signs){
            operator = part.getText();
        } else {
            throw new LanguageException("Expected name or signs, but got " + part.getClass().getSimpleName() + ".");
        }
    }
}
