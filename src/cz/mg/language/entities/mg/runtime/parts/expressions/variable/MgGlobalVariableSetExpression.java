package cz.mg.language.entities.mg.runtime.parts.expressions.variable;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.collections.list.ReadableList;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.components.variables.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.components.variables.MgVariable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;
import cz.mg.language.entities.mg.runtime.parts.expressions.MgExpression;
import cz.mg.language.entities.mg.runtime.utilities.DeclarationHelper;


public class MgGlobalVariableSetExpression extends MgVariableExpression implements MgVariableSetExpression {
    @Mandatory @Part
    private final MgExpression inputExpression;

    @Mandatory @Part
    private final MgInputConnector inputConnector;

    @Optional @Cache
    private int inputVariableOffset = -1;

    public MgGlobalVariableSetExpression(MgVariable variable, MgExpression inputExpression) {
        super(variable);
        this.inputExpression = inputExpression;
        this.inputConnector = MgVariableSetExpression.createInputConnector(variable);
        connect();
    }

    private void connect(){
        DeclarationHelper.connect(inputConnector, inputExpression.getOutputConnectors().iterator().next());
    }

    @Override
    public MgGlobalVariable getVariable() {
        return (MgGlobalVariable) super.getVariable();
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
        this.inputVariableOffset = inputConnector
            .getConnection()
            .getConnectionVariable()
            .getCache()
            .getOffset();
    }

    @Override
    public void run(MgFunctionInstance functionInstance) {
        getVariable().setObject(
            functionInstance.getObjects().get(inputVariableOffset)
        );
    }
}
