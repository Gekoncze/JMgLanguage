package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public class MgLogicalGroupExpression extends MgLogicalExpression {
    @Part
    private final List<MgLogicalExpression> expressions;

    public MgLogicalGroupExpression() {
        expressions = new List<>();
    }

    public MgLogicalGroupExpression(List<MgLogicalExpression> expressions) {
        this.expressions = expressions;
    }

    public List<MgLogicalExpression> getExpressions() {
        return expressions;
    }
}
