package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgInstanceVariableSetExpression extends MgVariableSetExpression {
    @Mandatory @Part
    private final MgExpression target;

    @Mandatory @Part
    private final MgInputConnector targetInputConnector;

    public MgInstanceVariableSetExpression(
        MgExpression target,
        MgInstanceVariable variable
    ) {
        super(variable);
        this.target = target;
        this.targetInputConnector = new MgInputConnector(
            new MgDatatype(
                variable.getParent(),
                MgDatatype.Storage.ANY,
                MgDatatype.Requirement.OPTIONAL
            )
        );
    }

    public MgExpression getTarget() {
        return target;
    }

    public MgInputConnector getTargetInputConnector() {
        return targetInputConnector;
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    protected MgCache createCache() {
        return new MgCache(
            new Array<>(getTargetInputConnector(), getInputConnector()),
            new Array<>()
        );
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        target.run(functionInstance);

        MgInstanceVariable targetVariable = getTargetInputConnector().getConnection().getConnectionVariable();
        MgInstanceVariable inputVariable = getInputConnector().getConnection().getConnectionVariable();
        MgObject targetObject = functionInstance.getObjects().get(targetVariable.getCache().getOffset());
        MgObject inputObject = functionInstance.getObjects().get(inputVariable.getCache().getOffset());
        ((MgStructuredInstance)targetObject).getObjects().set(inputObject, getVariable().getCache().getOffset());
    }
}
