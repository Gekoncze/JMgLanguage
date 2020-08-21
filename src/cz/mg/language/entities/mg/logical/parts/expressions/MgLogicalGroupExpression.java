package cz.mg.language.entities.mg.logical.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;


public class MgLogicalGroupExpression extends MgLogicalAbstractExpression {
    @Part
    private final List<MgLogicalAbstractExpression> expressions;

    public MgLogicalGroupExpression() {
        expressions = new List<>();
    }

    public MgLogicalGroupExpression(List<MgLogicalAbstractExpression> expressions) {
        this.expressions = expressions;
    }

    public List<MgLogicalAbstractExpression> getExpressions() {
        return expressions;
    }
}
