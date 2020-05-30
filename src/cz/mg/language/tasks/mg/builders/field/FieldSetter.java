package cz.mg.language.tasks.mg.builders.field;

import cz.mg.language.tasks.mg.builders.MgBuildTask;


public interface FieldSetter<Source extends MgBuildTask, Destination extends MgBuildTask> {
    public void set(Source source, Destination destination);
}
