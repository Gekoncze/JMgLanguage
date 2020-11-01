package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;


public class MgGlobalVariableGetExpression extends MgVariableExpression implements MgVariableGetExpression {
    @Mandatory @Part
    private final MgOutputConnector outputConnector;

    @Optional @Cache
    private int outputVariableOffset = -1;

    public MgGlobalVariableGetExpression(MgGlobalVariable variable) {
        super(variable);
        this.outputConnector = MgVariableGetExpression.createOutputConnector(variable);
    }

    @Override
    public MgGlobalVariable getVariable() {
        return (MgGlobalVariable) super.getVariable();
    }

    @Override
    protected @Mandatory ReadableCollection<MgExpression> getExpressions() {
        return new Array<>();
    }

    @Override
    protected @Mandatory ReadableCollection<MgInputConnector> getInputConnectors() {
        return new Array<>();
    }

    @Override
    protected @Mandatory ReadableCollection<MgOutputConnector> getOutputConnectors() {
        return new Array<>(outputConnector);
    }

    @Override
    public void validate() {
        super.validate();
        this.outputVariableOffset = outputConnector
            .getConnection()
            .getConnectionVariable()
            .getCache()
            .getOffset();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        functionInstance.getObjects().set(
            getVariable().getObject(),
            outputVariableOffset
        );
    }
}
