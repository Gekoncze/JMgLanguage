package cz.mg.language.tasks.builders.mg.pattern.expectations;

import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.tasks.builders.mg.field.FieldProcessor;


public class NameExpectation extends LeafExpectation {
    public NameExpectation(FieldProcessor fieldProcessor) {
        super(Name.class, null, fieldProcessor);
    }
}
