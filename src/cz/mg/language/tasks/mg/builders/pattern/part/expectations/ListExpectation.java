package cz.mg.language.tasks.mg.builders.pattern.part.expectations;

import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.part.Expectation;


public class ListExpectation extends Expectation {
    public ListExpectation(PartFieldProcessor fieldProcessor) {
        super(fieldProcessor);
    }

    @Override
    public boolean match(Part part) {
        if(part instanceof ListChain){
            return true;
        }
        return false;
    }
}
