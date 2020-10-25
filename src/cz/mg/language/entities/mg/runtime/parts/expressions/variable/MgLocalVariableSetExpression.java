package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.MgObject;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public class MgLocalVariableSetExpression extends MgVariableSetExpression {
    public MgLocalVariableSetExpression(MgInstanceVariable variable) {
        super(variable);
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    protected MgCache createCache() {
        return new MgCache(
            new Array<>(getInputConnector()),
            new Array<>()
        );
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();

        MgInstanceVariable inputVariable = getInputConnector().getConnection().getConnectionVariable();
        MgObject inputValue = functionInstance.getObjects().get(inputVariable.getCache().getOffset());
        functionInstance.getObjects().set(inputValue, getVariable().getCache().getOffset());
    }
}
