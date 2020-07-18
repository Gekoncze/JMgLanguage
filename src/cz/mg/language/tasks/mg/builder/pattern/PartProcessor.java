package cz.mg.language.tasks.mg.builder.pattern;

import cz.mg.language.tasks.mg.builder.MgBuildTask;
import cz.mg.language.tasks.mg.builder.part.MgBuildPartTask;


public class PartProcessor<S extends MgBuildPartTask, D extends MgBuildTask> extends Processor<S, D> {
    public PartProcessor(Class<S> sourceBuildTaskClass, Class<D> destinationBuildTaskClass, Setter<S, D> setter) {
        super(sourceBuildTaskClass, destinationBuildTaskClass, setter);
    }
}
