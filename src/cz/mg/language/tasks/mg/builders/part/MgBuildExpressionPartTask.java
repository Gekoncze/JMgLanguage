package cz.mg.language.tasks.mg.builders.part;

import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;
import cz.mg.language.entities.text.structured.parts.Part;


public class MgBuildExpressionPartTask extends MgBuildPartTask {
    @Output
    private MgLogicalExpression expression;

    public MgBuildExpressionPartTask(Part part) {
        super(part, Part.class);
    }

    public MgLogicalExpression getExpression() {
        return expression;
    }

    @Override
    protected void buildPart(Part part) {
        todo;
    }
}
