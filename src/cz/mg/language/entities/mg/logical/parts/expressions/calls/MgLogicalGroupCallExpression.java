package cz.mg.language.entities.mg.logical.parts.expressions.calls;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;


public class MgLogicalGroupCallExpression extends MgLogicalCallExpression {
    @Part
    private final List<MgLogicalCallExpression> expressions;

    public MgLogicalGroupCallExpression(List<MgLogicalCallExpression> expressions) {
        this.expressions = expressions;
    }

    public List<MgLogicalCallExpression> getExpressions() {
        return expressions;
    }
}
