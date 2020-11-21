package cz.mg.language.entities.mg.unresolved.parts.expressions.calls;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;


public class MgUnresolvedGroupCallExpression extends MgUnresolvedCallExpression {
    @Part
    private final List<MgUnresolvedCallExpression> expressions;

    public MgUnresolvedGroupCallExpression(List<MgUnresolvedCallExpression> expressions) {
        this.expressions = expressions;
    }

    public List<MgUnresolvedCallExpression> getExpressions() {
        return expressions;
    }
}
