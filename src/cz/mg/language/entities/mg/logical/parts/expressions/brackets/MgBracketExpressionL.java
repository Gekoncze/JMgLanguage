package cz.mg.language.entities.mg.logical.parts.expressions.brackets;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.expressions.MgExpressionL;


public abstract class MgBracketExpressionL extends MgExpressionL {
    @Part
    private final List<MgExpressionL> expressions = new List<>();

    public MgBracketExpressionL() {
    }

    public List<MgExpressionL> getExpressions() {
        return expressions;
    }
}
