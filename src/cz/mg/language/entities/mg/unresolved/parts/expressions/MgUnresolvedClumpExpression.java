package cz.mg.language.entities.mg.unresolved.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;


public class MgUnresolvedClumpExpression extends MgUnresolvedExpression {
    @Part
    private final List<MgUnresolvedExpression> expressions;

    public MgUnresolvedClumpExpression() {
        expressions = new List<>();
    }

    public MgUnresolvedClumpExpression(List<MgUnresolvedExpression> expressions) {
        this.expressions = expressions;
    }

    public List<MgUnresolvedExpression> getExpressions() {
        return expressions;
    }
}
