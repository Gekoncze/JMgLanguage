package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.objects.MgFunctionObject;


public class MgLogicalFunctionExpression extends MgExpression {
    @Mandatory @Part
    private final List<MgExpression> arguments;

    public MgLogicalFunctionExpression(List<MgExpression> arguments) {
        this.arguments = arguments;
    }

    public List<MgExpression> getArguments() {
        return arguments;
    }

    @Override
    public void run(MgFunctionObject functionObject) {
        // todo;
    }
}
