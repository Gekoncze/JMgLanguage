package cz.mg.language.tasks.mg.builder.pattern;

import cz.mg.language.tasks.mg.builder.block.MgBuildBlockTask;


public class BlockProcessor<S extends MgBuildBlockTask, D extends MgBuildBlockTask> extends Processor<S, D> {
    public BlockProcessor(Class<S> sourceBuildTaskClass, Class<D> destinationBuildTaskClass, Setter<S, D> setter) {
        super(sourceBuildTaskClass, destinationBuildTaskClass, setter);
    }
}
