package cz.mg.language.entities.mg.runtime.parts.expressions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.collections.array.ReadableArray;
import cz.mg.language.annotations.task.Cache;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public abstract class MgExpression implements MgRunnable {
    public static boolean DEBUG = true;

    @Optional @Cache
    private MgCache cache;

    public MgExpression() {
    }

    public MgCache getCache() {
        if(cache == null) cache = createCache();
        return cache;
    }

    protected abstract MgCache createCache();

    public void validate(){
        for(MgInputConnector inputConnector : getCache().getInputConnectors()){
            inputConnector.validate();
        }

        for(MgOutputConnector outputConnector : getCache().getOutputConnectors()){
            outputConnector.validate();
        }
    }

    public static class MgCache {
        @Mandatory @Shared
        private final ReadableArray<@Mandatory @Link MgInputConnector> inputConnectors;

        @Mandatory @Shared
        private final ReadableArray<@Mandatory @Link MgOutputConnector> outputConnectors;

        public MgCache(
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
    }
}