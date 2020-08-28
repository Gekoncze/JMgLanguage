package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgLogicalMemberExpression extends MgExpression {
    @Mandatory @Part
    private final MgExpression expression;

    @Mandatory @Part
    private final int variableIndex;

    public MgLogicalMemberExpression(MgExpression expression, int variableIndex) {
        this.expression = expression;
        this.variableIndex = variableIndex;
    }

    public MgExpression getExpression() {
        return expression;
    }

    public int getVariableIndex() {
        return variableIndex;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo
    }
}
