package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgInstanceVariableGetExpression extends MgVariableGetExpression {
    @Mandatory @Part
    private final MgExpression target;

    @Mandatory @Part
    private final MgInputConnector targetInputConnector;

    public MgInstanceVariableGetExpression(
        MgExpression target,
        MgStructuredType structuredType,
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
            new Array<>(getTargetInputConnector()),
            new Array<>(getOutputConnector())
        );
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        target.run(functionInstance);

        MgInstanceVariable targetVariable = getTargetInputConnector().getConnection().getConnectionVariable();
        MgStructuredInstance parent = (MgStructuredInstance) functionInstance.getObjects().get(targetVariable.getCache().getOffset());
        MgObject child = parent.getObjects().get(getVariable().getCache().getOffset());
        MgInstanceVariable outputVariable = getOutputConnector().getConnection().getConnectionVariable();
        functionInstance.getObjects().set(child, outputVariable.getCache().getOffset());
    }

    public static ReadableArray<MgInputConnector> createInputInterface(MgStructuredType structuredType){
        return new Array<>(new MgInputConnector(
            new MgDatatype(
                structuredType,
                MgDatatype.Storage.ANY,
                MgDatatype.Requirement.OPTIONAL
            )
        ));
    }

    public static ReadableArray<MgOutputConnector> createOutputInterface(MgInstanceVariable variable){
        return new Array<>(new MgOutputConnector(variable.getDatatype()));
    }
}
