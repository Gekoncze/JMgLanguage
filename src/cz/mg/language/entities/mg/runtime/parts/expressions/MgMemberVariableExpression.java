package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgClassVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;
import cz.mg.language.entities.mg.runtime.roles.MgClassInstance;
import cz.mg.language.entities.mg.runtime.roles.MgObject;


public class MgMemberVariableExpression extends MgExpression {
    @Mandatory @Part
    private final MgExpression target;

    @Mandatory @Link
    private final MgClassVariable variable;

    @Mandatory @Link
    private final MgFunctionVariable input;

    @Mandatory @Link
    private final MgFunctionVariable output;

    public MgMemberVariableExpression(
        MgExpression target,
        MgClassVariable variable,
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

    public MgClassVariable getVariable() {
        return variable;
    }

    public MgFunctionVariable getInput() {
        return input;
    }

    public MgFunctionVariable getOutput() {
        return output;
    }

    @Override
    public void run(MgFunctionInstanceImpl functionObject){
        target.run(functionObject);
        MgClassInstance classObject = (MgClassInstance) functionObject.getObjects().get(input.getOffset());
        MgObject memberObject = classObject.getObjects().get(variable.getOffset());
        functionObject.getObjects().set(memberObject, output.getOffset());
    }
}
