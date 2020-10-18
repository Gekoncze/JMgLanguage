package cz.mg.language.entities.mg.runtime.parts.expressions.function;

import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgInstanceFunctionExpression extends MgFunctionExpression {
    @Mandatory @Part
    private final MgExpression target;

    public MgInstanceFunctionExpression(MgExpression target, MgFunction function, MgExpression expression) {
        super(function, expression);
        this.target = target;
    }

    public MgExpression getTarget() {
        return target;
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        target.run(functionInstance);
        super.run(functionInstance);
    }
}
