package cz.mg.language.tasks.builders.mg;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.text.structured.parts.Part;


public abstract class MgBuildPartTask extends MgBuildTask {
    @Input
    protected final Part part;

    public MgBuildPartTask(Part part) {
        this.part = part;
    }
}
