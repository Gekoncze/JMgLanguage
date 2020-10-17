package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.collections.array.ReadableArray;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public abstract class MgExpression implements MgRunnable {
    public static boolean DEBUG = true;

    @Mandatory @Part
    private final ReadableArray<MgInputConnector> inputConnectors;

    @Mandatory @Part
    private final ReadableArray<MgOutputConnector> outputConnectors;

    public MgExpression(
        ReadableArray<MgInputConnector> inputConnectors,
        ReadableArray<MgOutputConnector> outputConnectors
    ) {
        this.inputConnectors = inputConnectors;
        this.outputConnectors = outputConnectors;
    }

    public ReadableArray<MgInputConnector> getInputConnectors() {
        return inputConnectors;
    }

    public ReadableArray<MgOutputConnector> getOutputConnectors() {
        return outputConnectors;
    }

    public void validate(){
        for(MgInputConnector inputConnector : inputConnectors){
            inputConnector.validate();
        }

        for(MgOutputConnector outputConnector : outputConnectors){
            outputConnector.validate();
        }
    }
}