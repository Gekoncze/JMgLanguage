package cz.mg.language.tasks.mg.builders.pattern.part;

import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;


public abstract class Expectation {
    @cz.mg.language.annotations.entity.Part
    private final PartFieldProcessor fieldProcessor;

    public Expectation(PartFieldProcessor fieldProcessor) {
        this.fieldProcessor = fieldProcessor;
    }

    public PartFieldProcessor getFieldProcessor() {
        return fieldProcessor;
    }

    public abstract boolean match(Part part);
}
