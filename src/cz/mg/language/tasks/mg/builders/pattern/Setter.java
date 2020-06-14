package cz.mg.language.tasks.mg.builders.pattern;

import cz.mg.language.tasks.mg.builders.MgBuildTask;


public interface Setter<S extends MgBuildTask, D extends MgBuildTask> {
    void set(S source, D destination);
}