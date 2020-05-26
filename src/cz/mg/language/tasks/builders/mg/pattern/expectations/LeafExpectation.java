package cz.mg.language.tasks.builders.mg.pattern.expectations;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Leaf;


public class LeafExpectation extends ClassExpectation {
    @Value
    private final ReadableText value;

    public LeafExpectation(Class<? extends Leaf> clazz, ReadableText value) {
        super(clazz);
        this.value = value;
    }

    @Override
    public boolean match(Part part) {
        if(super.match(part)){
            if(value != null){
                if(((Leaf) part).getText().equals(value)){
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }
}
