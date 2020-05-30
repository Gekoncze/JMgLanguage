package cz.mg.language.tasks.mg.builders.pattern.token;

import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;


public abstract class Expectation {
    @cz.mg.language.annotations.entity.Part
    private final FieldProcessor fieldProcessor;

    public Expectation(FieldProcessor fieldProcessor) {
        this.fieldProcessor = fieldProcessor;
    }

    public abstract boolean match(Part part);
}
