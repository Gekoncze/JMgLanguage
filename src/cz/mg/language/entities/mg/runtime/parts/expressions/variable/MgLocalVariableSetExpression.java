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
import cz.mg.language.tasks.mg.resolver.command.utilities.DeclarationHelper;


public class MgLocalVariableSetExpression extends MgVariableExpression implements MgVariableSetExpression {
    @Mandatory @Part
    private final MgExpression inputExpression;

    @Mandatory @Part
    private final MgInputConnector inputConnector;

    @Optional @Cache
    private int localVariableOffset = -1;

    @Optional @Cache
    private int inputVariableOffset = -1;

    public MgLocalVariableSetExpression(MgVariable variable, MgExpression inputExpression) {
        super(variable);
        this.inputExpression = inputExpression;
        this.inputConnector = MgVariableSetExpression.createInputConnector(variable);
        connect();
    }

    private void connect(){
        DeclarationHelper.connect(inputConnector, getOutputConnectors(inputExpression).iterator().next());
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    protected ReadableCollection<MgExpression> getExpressions() {
        return new Array<>(inputExpression);
    }

    @Override
    protected ReadableCollection<MgInputConnector> getInputConnectors() {
        return new Array<>(inputConnector);
    }

    @Override
    protected ReadableCollection<MgOutputConnector> getOutputConnectors() {
        return new Array<>();
    }

    @Override
    public void validate() {
        super.validate();
        this.localVariableOffset = getVariable()
            .getCache()
            .getOffset();
        this.inputVariableOffset = inputConnector
            .getConnection()
            .getConnectionVariable()
            .getCache()
            .getOffset();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        inputExpression.run(functionInstance);

        functionInstance.getObjects().set(
            functionInstance.getObjects().get(inputVariableOffset),
            localVariableOffset
        );
    }
}
