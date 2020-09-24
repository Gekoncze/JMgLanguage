package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgMemberFunctionExpression extends MgFunctionExpression {
    @Mandatory @Part
    private final MgExpression target;

    public MgMemberFunctionExpression(
        MgExpression target,
        MgFunction function,
        MgExpression expression,
        List<MgLocalVariable> input,
        List<MgLocalVariable> output
    ) {
        super(function, expression, input, output);
        this.target = target;
    }

    public MgExpression getTarget() {
        return target;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        target.run(functionObject);
        super.run(functionObject);
    }
}











