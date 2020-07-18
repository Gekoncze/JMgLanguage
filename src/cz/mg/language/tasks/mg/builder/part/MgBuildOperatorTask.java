package cz.mg.language.tasks.mg.builder.part;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Signs;


public class MgBuildOperatorTask extends MgBuildPartTask<Signs> {
    @Output
    private ReadableText operator;

    public MgBuildOperatorTask(Part part) {
        super(part, Signs.class);
    }

    public ReadableText getOperator() {
        return operator;
    }

    @Override
    protected void buildPart(Signs part) {
        operator = part.getText();
    }
}
