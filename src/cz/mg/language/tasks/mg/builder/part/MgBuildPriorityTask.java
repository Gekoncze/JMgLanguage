package cz.mg.language.tasks.mg.builder.part;

import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Value;


public class MgBuildPriorityTask extends MgBuildPartTask<Value> {
    @Output
    private int priority;

    public MgBuildPriorityTask(Part part) {
        super(part, Value.class);
    }

    public int getPriority() {
        return priority;
    }

    @Override
    protected void buildPart(Value part) {
        try {
            priority = part.getText().parseInt();
        } catch (Exception e){
            throw new LanguageException("Could not parse priority: " + e.getMessage());
        }
    }
}
