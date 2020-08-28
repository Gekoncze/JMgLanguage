package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgFunctionExpression extends MgExpression {
    @Mandatory @Part
    private final MgFunction function;

    @Mandatory @Part
    private final List<MgExpression> arguments;

    public MgFunctionExpression(MgFunction function, List<MgExpression> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    public MgFunction getFunction() {
        return function;
    }

    public List<MgExpression> getArguments() {
        return arguments;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo;
    }
}
