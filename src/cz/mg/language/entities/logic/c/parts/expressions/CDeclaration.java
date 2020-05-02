package cz.mg.language.entities.logic.c.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.logic.c.parts.CVariable;


public class CDeclaration extends CExpression {
    @Part
    private final CVariable variable;

    public CDeclaration(CVariable variable) {
        this.variable = variable;
    }

    public CVariable getVariable() {
        return variable;
    }
}
