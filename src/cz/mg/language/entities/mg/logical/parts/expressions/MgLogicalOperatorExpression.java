package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public class MgLogicalOperatorExpression extends MgLogicalExpression {
    @Part
    private final List<MgLogicalExpression> expressions;

    public MgLogicalOperatorExpression() {
        this(new List<>());
    }

    public MgLogicalOperatorExpression(List<MgLogicalExpression> expressions) {
        this.expressions = expressions;
    }

    public List<MgLogicalExpression> getExpressions() {
        return expressions;
    }
}
