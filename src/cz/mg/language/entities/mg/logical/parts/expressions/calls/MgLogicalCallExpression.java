package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public abstract class MgLogicalCallExpression extends MgLogicalExpression {
    @Part
    private final List<MgLogicalExpression> expressions = new List<>();

    public MgLogicalCallExpression() {
    }

    public List<MgLogicalExpression> getExpressions() {
        return expressions;
    }
}
