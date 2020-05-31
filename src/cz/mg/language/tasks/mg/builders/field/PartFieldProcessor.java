package cz.mg.language.tasks.mg.builders.field;

import cz.mg.language.tasks.mg.builders.MgBuildTask;


public class PartFieldProcessor<Source extends MgBuildTask, Destination extends MgBuildTask> extends FieldProcessor<Source, Destination> {
    public PartFieldProcessor(Class<? extends Source> source, Class<? extends Destination> destination, FieldSetter<Source, Destination> setter) {
        super(source, destination, setter);
    }
}
