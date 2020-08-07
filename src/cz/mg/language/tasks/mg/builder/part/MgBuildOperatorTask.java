package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.MgLogicalOperator;
import cz.mg.language.entities.text.structured.Part;
import cz.mg.language.entities.text.structured.parts.Leaf;
import cz.mg.language.entities.text.structured.parts.leaves.Operator;
import cz.mg.language.entities.text.structured.parts.leaves.names.ObjectName;


public abstract class MgBuildOperatorTask extends MgBuildPartTask {
    @Output
    private MgLogicalOperator operator;

    public MgBuildOperatorTask(List<Part> parts) {
        super(parts);
    }

    public MgLogicalOperator getOperator() {
        return operator;
    }

    @Override
    protected void buildParts(List<Part> parts) {
        checkCount(1);
        Leaf leaf = get(Leaf.class, 0);
        if(leaf instanceof ObjectName || leaf instanceof Operator){
            operator = new MgLogicalOperator(leaf.getText());
            operator.setType(getType());
        } else {
            throw new LanguageException("Expected name or signs, but got " + leaf.getClass().getSimpleName() + ".");
        }
    }

    protected abstract MgLogicalOperator.Type getType();

}
