package cz.mg.language.tasks.mg.builders.pattern;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.array.Array;
import cz.mg.language.entities.text.structured.parts.Part;


public class Pattern {
    @cz.mg.language.annotations.entity.Part
    private final Array<Expectation> expectations;

    public Pattern(Expectation... expectations) {
        this.expectations = new Array<>(expectations);
    }

    public boolean match(ReadableCollection<Part> parts){
        if(!(expectations.count() == parts.count())) return false;
        int i = 0;
        for(Part part : parts){
            if(!expectations.get(i).match(part)) return false;
            i++;
        }
        return true;
    }
}
