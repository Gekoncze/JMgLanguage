package cz.mg.language.entities.c.logical.parts.expressions;

import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.c.logical.parts.CVariable;


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
