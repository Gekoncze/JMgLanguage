package cz.mg.language.tasks.mg.builders.pattern.part.expectations;

import cz.mg.language.entities.text.structured.parts.leaves.Name;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;


public class NameExpectation extends LeafExpectation {
    public NameExpectation(PartFieldProcessor fieldProcessor) {
        super(Name.class, null, fieldProcessor);
    }
}
