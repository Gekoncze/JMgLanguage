package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Cache;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.utilities.DeclarationHelper;


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
        DeclarationHelper.connect(inputConnector, inputExpression.getOutputConnectors().iterator().next());
    }

    @Override
    public MgInstanceVariable getVariable() {
        return (MgInstanceVariable) super.getVariable();
    }

    @Override
    public ReadableList<MgExpression> getExpressions() {
        return new List<>(inputExpression);
    }

    @Override
    public ReadableList<MgInputConnector> getInputConnectors() {
        return new List<>(inputConnector);
    }

    @Override
    public ReadableList<MgOutputConnector> getOutputConnectors() {
        return new List<>();
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
