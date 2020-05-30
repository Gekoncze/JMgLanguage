package cz.mg.language.tasks.mg.builders.part;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.MgBuildTask;


public abstract class MgBuildPartTask extends MgBuildTask {
    @Input
    protected final Part part;

    public MgBuildPartTask(Part part) {
        this.part = part;
    }
}
