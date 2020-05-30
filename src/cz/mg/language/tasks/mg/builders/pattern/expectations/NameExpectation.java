package cz.mg.language.tasks.mg.builders.pattern.expectations;

import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;


public class NameExpectation extends LeafExpectation {
    public NameExpectation(FieldProcessor fieldProcessor) {
        super(Name.class, null, fieldProcessor);
    }
}
