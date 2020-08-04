package cz.mg.language.tasks.mg.builder.part;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalOperator;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Leaf;
import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.entities.text.structured.parts.leaves.Signs;


public class MgBuildRightOperatorTask extends MgBuildPartTask<Leaf> {
    @Output
    private MgLogicalOperator operator;

    public MgBuildRightOperatorTask(Part part) {
        super(part, Leaf.class);
    }

    public MgLogicalOperator getOperator() {
        return operator;
    }

    @Override
    protected void buildPart(Leaf part) {
        if(part instanceof Name || part instanceof Signs){
            operator = new MgLogicalOperator(part.getText());
            operator.setType(MgLogicalOperator.Type.RIGHT);
        } else {
            throw new LanguageException("Expected name or signs, but got " + part.getClass().getSimpleName() + ".");
        }
    }
}
