package cz.mg.language.tasks.mg.builders.part;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.text.structured.parts.Part;


public class MgBuildExpressionPartTask extends MgBuildPartTask {
    @Output
    private final MgLogicalExpression output;

    public MgBuildExpressionPartTask(Part part) {
        super(part);
    }

    public MgLogicalExpression getOutput() {
        return output;
    }

    @Override
    protected void onRun() {
        todo;
    }
}
