package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public class MgClassFunctionExpression extends MgFunctionExpression {
    @Mandatory @Part
    private final MgExpression target;

    public MgClassFunctionExpression(
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
    public void run(MgFunctionInstance functionInstance) {
        target.run(functionInstance);
        super.run(functionInstance);
    }
}











