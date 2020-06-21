package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;

public class MgLogicalPathExpression extends MgLogicalExpression {
    @Part
    private final List<MgLogicalExpression> expressions;

    public MgLogicalPathExpression() {
        this(new List<>());
    }

    public MgLogicalPathExpression(List<MgLogicalExpression> expressions) {
        this.expressions = expressions;
    }

    public List<MgLogicalExpression> getExpressions() {
        return expressions;
    }
}
