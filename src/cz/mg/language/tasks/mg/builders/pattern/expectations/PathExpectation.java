package cz.mg.language.tasks.mg.builders.pattern.expectations;

import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.PathChain;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.Expectation;


public class PathExpectation extends Expectation {
    public PathExpectation(FieldProcessor fieldProcessor) {
        super(fieldProcessor);
    }

    @Override
    public boolean match(Part part) {
        if(part instanceof PathChain){
            return true;
        }
        return false;
    }
}
