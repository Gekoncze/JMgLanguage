package cz.mg.language.tasks.mg.builders.pattern.token.expectations;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.leaves.Leaf;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;


public class LeafExpectation extends ClassExpectation {
    @Value
    private final ReadableText value;

    public LeafExpectation(Class<? extends Leaf> clazz, ReadableText value, FieldProcessor fieldProcessor) {
        super(clazz, fieldProcessor);
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
