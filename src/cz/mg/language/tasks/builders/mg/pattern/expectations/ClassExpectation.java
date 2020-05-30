package cz.mg.language.tasks.builders.mg.pattern.expectations;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.builders.mg.field.FieldProcessor;
import cz.mg.language.tasks.builders.mg.pattern.Expectation;


public class ClassExpectation extends Expectation {
    @Link
    private final Class<? extends Part> clazz;

    public ClassExpectation(Class<? extends Part> clazz, FieldProcessor fieldProcessor) {
        super(fieldProcessor);
        this.clazz = clazz;
    }

    @Override
    public boolean match(Part part) {
        return clazz.isInstance(part);
    }
}
