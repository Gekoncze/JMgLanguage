package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgInstanceVariableGetExpression extends MgVariableExpression {
    public MgInstanceVariableGetExpression(MgStructuredType structuredType, MgInstanceVariable variable) {
        super(createInputInterface(structuredType), createOutputInterface(variable), variable);
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        MgFunctionVariable parentVariable = getInputConnectors().getFirst().getConnection().getConnectionVariable();
        MgStructuredInstance parent = (MgStructuredInstance) functionInstance.getObjects().get(parentVariable.getOffset());
        MgObject child = parent.getObjects().get(getVariable().getOffset());
        MgFunctionVariable outputVariable = getOutputConnectors().getFirst().getConnection().getConnectionVariable();
        functionInstance.getObjects().set(child, outputVariable.getOffset());
    }

    public static ReadableArray<MgInputConnector> createInputInterface(MgStructuredType structuredType){
        return new Array<>(new MgInputConnector(
            new MgDatatype(
                structuredType,
                MgDatatype.Storage.OTHER,
                MgDatatype.Requirement.OPTIONAL
            )
        ));
    }

    public static ReadableArray<MgOutputConnector> createOutputInterface(MgInstanceVariable variable){
        return new Array<>(new MgOutputConnector(variable.getDatatype()));
    }
}
