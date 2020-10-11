package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgInstanceVariableExpression extends MgVariableExpression {
    @Mandatory @Part
    private final MgExpression target;

    @Mandatory @Link
    private final MgInstanceVariable variable;

    @Mandatory @Link
    private final MgFunctionVariable input;

    @Mandatory @Link
    private final MgFunctionVariable output;

    public MgInstanceVariableExpression(
        MgExpression target,
        MgInstanceVariable variable,
        MgFunctionVariable input,
        MgFunctionVariable output
    ) {
        this.target = target;
        this.variable = variable;
        this.input = input;
        this.output = output;
    }

    public MgExpression getTarget() {
        return target;
    }

    @Override
    public MgInstanceVariable getVariable() {
        return variable;
    }

    public MgFunctionVariable getInput() {
        return input;
    }

    public MgFunctionVariable getOutput() {
        return output;
    }

    @Override
    public MgStructuredInstance getParent(MgFunctionInstance functionInstance) {
        return (MgStructuredInstance) functionInstance.getObjects().get(input.getOffset());
    }

    @Override
    public void run(MgFunctionInstance functionInstance){
        target.run(functionInstance);
        MgObject object = getParent(functionInstance).getObjects().get(variable.getOffset());
        functionInstance.getObjects().set(object, output.getOffset());
    }
}
