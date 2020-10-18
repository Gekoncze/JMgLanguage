package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.entities.mg.runtime.components.types.MgStructuredType;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgInstanceVariableSetExpression extends MgVariableExpression {
    public MgInstanceVariableSetExpression(MgStructuredType structuredType, MgInstanceVariable variable) {
        super(createInputInterface(structuredType, variable), createOutputInterface(), variable);
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        MgFunctionVariable parentVariable = getInputConnectors().getFirst().getConnection().getConnectionVariable();
        MgFunctionVariable inputValueVariable = getInputConnectors().getLast().getConnection().getConnectionVariable();
        MgStructuredInstance parent = (MgStructuredInstance) functionInstance.getObjects().get(parentVariable.getOffset());
        parent.getObjects().set(functionInstance.getObjects().get(inputValueVariable.getOffset()), getVariable().getOffset());
    }

    public static ReadableArray<MgInputConnector> createInputInterface(MgStructuredType structuredType, MgInstanceVariable variable){
        return new Array<>(
            new MgInputConnector(
                new MgDatatype(
                    structuredType,
                    MgDatatype.Storage.OTHER,
                    MgDatatype.Requirement.OPTIONAL
                )
            ),
            new MgInputConnector(variable.getDatatype())
        );
    }

    public static ReadableArray<MgOutputConnector> createOutputInterface(){
        return new Array<>();
    }
}
