package cz.mg.language.tasks.mg.builders.pattern.part.expectations;

import cz.mg.language.entities.text.structured.parts.Declaration;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.part.Expectation;


public class DeclarationExpectation extends Expectation {
    public DeclarationExpectation(PartFieldProcessor fieldProcessor) {
        super(fieldProcessor);
    }

    @Override
    public boolean match(Part part) {
        return part instanceof Declaration;
    }
}
