package cz.mg.language.entities.mg.runtime.parts.expressions;


import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgFunction;


public abstract class MgOperatorExpression extends MgExpression {
    @Mandatory @Part
    private final MgFunction function;

    public MgOperatorExpression(MgFunction function) {
        this.function = function;
    }

    public MgFunction getFunction() {
        return function;
    }
}
