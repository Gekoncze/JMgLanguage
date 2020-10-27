package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.List;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public abstract class MgExpression implements MgRunnable {
    private static final boolean DEBUG = true;

    @Mandatory @Part
    private final List<@Mandatory @Part MgExpression> expressions = new List<>();

    @Mandatory @Part
    private final ReadableArray<@Mandatory @Part MgInputConnector> inputConnectors;

    @Mandatory @Part
    private final ReadableArray<@Mandatory @Part MgOutputConnector> outputConnectors;

    public MgExpression(
        ReadableArray<MgInputConnector> inputConnectors,
        ReadableArray<MgOutputConnector> outputConnectors
    ) {
        this.inputConnectors = inputConnectors;
        this.outputConnectors = outputConnectors;
    }

    public final List<MgExpression> getExpressions() {
        return expressions;
    }

    public final ReadableArray<MgInputConnector> getInputConnectors() {
        return inputConnectors;
    }

    public final ReadableArray<MgOutputConnector> getOutputConnectors() {
        return outputConnectors;
    }

    public void validate(){
        for(MgInputConnector inputConnector : getInputConnectors()){
            inputConnector.validate();
        }

        for(MgOutputConnector outputConnector : getOutputConnectors()){
            outputConnector.validate();
        }
    }

    @Override
    public final void run(MgFunctionInstance functionInstance) {
        if(DEBUG) validate();
        onRunExpressions(functionInstance);
        onRun(functionInstance);
    }

    protected void onRunExpressions(MgFunctionInstance functionInstance){
        for(MgExpression expression : expressions){
            expression.run(functionInstance);
        }
        onRun(functionInstance);
    }

    protected abstract void onRun(MgFunctionInstance functionInstance);
}