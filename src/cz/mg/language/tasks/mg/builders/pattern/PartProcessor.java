package cz.mg.language.tasks.mg.builders.pattern;

import cz.mg.language.tasks.mg.builders.MgBuildTask;
import cz.mg.language.tasks.mg.builders.part.MgBuildPartTask;


public class PartProcessor<S extends MgBuildPartTask, D extends MgBuildTask> extends Processor<S, D> {
    public PartProcessor(Class<S> sourceBuildTaskClass, Class<D> destinationBuildTaskClass, Setter<S, D> setter) {
        super(sourceBuildTaskClass, destinationBuildTaskClass, setter);
    }
}
