package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgLogicalVariableExpression extends MgExpression {
    @Mandatory @Part
    private final int variableIndex;

    public MgLogicalVariableExpression(int variableIndex) {
        this.variableIndex = variableIndex;
    }

    public int getVariableIndex() {
        return variableIndex;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo - consideration - maybe could also pass source object somehow?
        // todo
    }
}
