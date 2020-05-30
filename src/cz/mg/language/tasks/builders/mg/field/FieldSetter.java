package cz.mg.language.tasks.builders.mg.field;

import cz.mg.language.entities.logic.mg.MgEntityL;
import cz.mg.language.tasks.builders.mg.MgBuildTask;


public interface FieldSetter {
    public void set(MgBuildTask source, MgEntityL destination);
}
