package cz.mg.language.entities.mg.logical.parts.expressions.chains;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgExpressionL;


public class MgListExpressionL extends MgChainExpressionL {
    @Part
    private final List<MgExpressionL> expressions = new List<>();

    public MgListExpressionL() {
    }

    public List<MgExpressionL> getExpressions() {
        return expressions;
    }
}
