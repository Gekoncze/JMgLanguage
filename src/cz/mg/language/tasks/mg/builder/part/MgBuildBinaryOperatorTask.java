package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.logical.parts.MgLogicalOperator;
import cz.mg.language.entities.text.structured.Part;


public class MgBuildBinaryOperatorTask extends MgBuildOperatorTask {
    public MgBuildBinaryOperatorTask(List<Part> parts) {
        super(parts);
    }

    @Override
    protected MgLogicalOperator.Type getType() {
        return MgLogicalOperator.Type.BINARY;
    }
}
