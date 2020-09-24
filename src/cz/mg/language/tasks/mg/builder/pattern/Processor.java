package cz.mg.language.tasks.mg.builder.pattern;

import cz.mg.language.tasks.mg.builder.MgBuildTask;


public abstract class Processor<S extends MgBuildTask, D extends MgBuildTask> {
    @cz.mg.language.annotations.storage.Part
    private final Class<S> sourceBuildTaskClass;

    @cz.mg.language.annotations.storage.Part
    private final Class<D> destinationBuildTaskClass;

    @cz.mg.language.annotations.storage.Part
    private final Setter<S, D> setter;

    public Processor(Class<S> sourceBuildTaskClass, Class<D> destinationBuildTaskClass, Setter<S, D> setter) {
        this.sourceBuildTaskClass = sourceBuildTaskClass;
        this.destinationBuildTaskClass = destinationBuildTaskClass;
        this.setter = setter;
    }

    public Class<S> getSourceBuildTaskClass() {
        return sourceBuildTaskClass;
    }

    public Class<D> getDestinationBuildTaskClass() {
        return destinationBuildTaskClass;
    }

    public Setter<S, D> getSetter() {
        return setter;
    }
}
