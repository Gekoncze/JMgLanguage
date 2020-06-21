package cz.mg.language.tasks.mg.builders.pattern;

import cz.mg.language.tasks.mg.builders.block.MgBuildBlockTask;


public class BlockProcessor<S extends MgBuildBlockTask, D extends MgBuildBlockTask> extends Processor<S, D> {
    public BlockProcessor(Class<S> sourceBuildTaskClass, Class<D> destinationBuildTaskClass, Setter<S, D> setter) {
        super(sourceBuildTaskClass, destinationBuildTaskClass, setter);
    }
}
