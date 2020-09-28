package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;


public class MgMemberFunctionExpression extends MgFunctionExpression {
    @Mandatory @Part
    private final MgExpression target;

    public MgMemberFunctionExpression(
        MgExpression target,
        MgFunction function,
        MgExpression expression,
        List<MgFunctionVariable> input,
        List<MgFunctionVariable> output
    ) {
        super(function, expression, input, output);
        this.target = target;
    }

    public MgExpression getTarget() {
        return target;
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject) {
        target.run(functionObject);
        super.run(functionObject);
    }
}











