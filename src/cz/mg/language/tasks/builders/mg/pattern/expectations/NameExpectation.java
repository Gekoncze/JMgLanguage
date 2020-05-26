package cz.mg.language.tasks.builders.mg.pattern.expectations;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.structured.parts.leaves.Name;


public class NameExpectation extends LeafExpectation {
    public NameExpectation(ReadableText value) {
        super(Name.class, value);
    }
}
