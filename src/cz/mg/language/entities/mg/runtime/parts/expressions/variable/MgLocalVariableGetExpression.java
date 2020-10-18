package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class MgLocalVariableGetExpression extends MgVariableExpression {
    public MgLocalVariableGetExpression(MgFunctionVariable variable) {
        super(createInputInterface(), createOutputInterface(variable), variable);
    }

    @Override
    public MgFunctionVariable getVariable() {
        return (MgFunctionVariable) super.getVariable();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();
        // nothing to do
    }

    public static ReadableArray<MgInputConnector> createInputInterface(){
        return new Array<>();
    }

    public static ReadableArray<MgOutputConnector> createOutputInterface(@Mandatory MgVariable variable){
        return new Array<>(new MgOutputConnector(variable.getDatatype()));
    }
}
