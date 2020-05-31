package cz.mg.language.entities.mg.logical.parts.expressions.chains;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgLogicalExpression;


public class MgLogicalPathExpression extends MgLogicalChainExpression {
    @Part
    private final List<MgLogicalExpression> expressions = new List<>();

    public MgLogicalPathExpression() {
    }

    public List<MgLogicalExpression> getExpressions() {
        return expressions;
    }
}
