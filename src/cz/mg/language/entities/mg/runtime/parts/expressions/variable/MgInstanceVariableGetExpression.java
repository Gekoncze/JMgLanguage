package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.instances.MgStructuredInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.tasks.mg.resolver.command.utilities.DeclarationHelper;


public class MgInstanceVariableGetExpression extends MgVariableExpression implements MgVariableGetExpression {
    @Mandatory @Part
    private final MgExpression targetExpression;

    @Mandatory @Part
    private final MgInputConnector targetConnector;

    @Mandatory @Part
    private final MgOutputConnector outputConnector;

    @Optional @Cache
    private int instanceVariableOffset = -1;

    @Optional @Cache
    private int targetVariableOffset = -1;

    @Optional @Cache
    private int outputVariableOffset = -1;

    public MgInstanceVariableGetExpression(MgInstanceVariable variable, MgExpression targetExpression) {
        super(variable);
        this.targetExpression = targetExpression;
        this.targetConnector = MgInstanceVariableExpression.createInputConnector(variable);
        this.outputConnector = MgVariableGetExpression.createOutputConnector(variable);
        connect();
    }

    private void connect(){
        DeclarationHelper.connect(targetConnector, getOutputConnectors(targetExpression).iterator().next());
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    protected ReadableList<MgExpression> getExpressions() {
        return new List<>(targetExpression);
    }

    @Override
    protected ReadableList<MgInputConnector> getInputConnectors() {
        return new List<>(targetConnector);
    }

    @Override
    protected ReadableList<MgOutputConnector> getOutputConnectors() {
        return new List<>(outputConnector);
    }

    @Override
    public void validate() {
        super.validate();
        this.instanceVariableOffset = getVariable()
            .getCache()
            .getOffset();
        this.targetVariableOffset = targetConnector
            .getConnection()
            .getConnectionVariable()
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
        targetExpression.run(functionInstance);

        MgStructuredInstance targetObject = (MgStructuredInstance) functionInstance.getObjects().get(targetVariableOffset);
        functionInstance.getObjects().set(
            targetObject.getObjects().get(instanceVariableOffset),
            outputVariableOffset
        );
    }
}
