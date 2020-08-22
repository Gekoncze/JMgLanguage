package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public class MgLogicalClumpExpression extends MgLogicalExpression {
    @Part
    private final List<MgLogicalExpression> expressions;

    public MgLogicalClumpExpression() {
        expressions = new List<>();
    }

    public MgLogicalClumpExpression(List<MgLogicalExpression> expressions) {
        this.expressions = expressions;
    }

    public List<MgLogicalExpression> getExpressions() {
        return expressions;
    }
}
