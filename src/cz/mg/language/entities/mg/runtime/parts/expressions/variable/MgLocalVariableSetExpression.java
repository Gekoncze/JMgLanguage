package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgLocalVariableSetExpression extends MgVariableExpression {
    public MgLocalVariableSetExpression(MgFunctionVariable variable) {
        super(createInputInterface(variable), createOutputInterface(), variable);
    }

    @Override
    public MgFunctionVariable getVariable() {
        return (MgFunctionVariable) super.getVariable();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        MgFunctionVariable inputVariable = getInputConnectors().getFirst().getConnection().getConnectionVariable();
        MgObject inputValue = functionInstance.getObjects().get(inputVariable.getOffset());
        functionInstance.getObjects().set(inputValue, getVariable().getOffset());
    }

    public static ReadableArray<MgInputConnector> createInputInterface(@Mandatory MgVariable variable){
        return new Array<>(new MgInputConnector(variable.getDatatype()));
    }

    public static ReadableArray<MgOutputConnector> createOutputInterface(){
        return new Array<>();
    }
}
