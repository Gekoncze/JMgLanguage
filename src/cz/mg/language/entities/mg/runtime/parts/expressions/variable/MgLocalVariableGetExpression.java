package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgLocalVariableGetExpression extends MgVariableExpression implements MgVariableGetExpression {
    @Mandatory @Part
    private final MgOutputConnector outputConnector;

    @Optional @Cache
    private int localVariableOffset = -1;

    @Optional @Cache
    private int outputVariableOffset = -1;

    public MgLocalVariableGetExpression(MgVariable variable) {
        super(variable);
        this.outputConnector = MgVariableGetExpression.createOutputConnector(variable);
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    protected ReadableCollection<MgExpression> getExpressions() {
        return new Array<>();
    }

    @Override
    protected ReadableCollection<MgInputConnector> getInputConnectors() {
        return new Array<>();
    }

    @Override
    protected ReadableCollection<MgOutputConnector> getOutputConnectors() {
        return new Array<>(outputConnector);
    }

    @Override
    public void validate() {
        super.validate();
        this.localVariableOffset = getVariable()
            .getCache()
            .getOffset();
        this.outputVariableOffset = outputConnector
            .getConnection()
            .getConnectionVariable()
            .getCache()
            .getOffset();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        functionInstance.getObjects().set(
            functionInstance.getObjects().get(localVariableOffset),
            outputVariableOffset
        );
    }
}
