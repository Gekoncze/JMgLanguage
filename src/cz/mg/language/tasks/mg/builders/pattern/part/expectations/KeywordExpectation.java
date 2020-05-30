package cz.mg.language.tasks.mg.builders.pattern.part.expectations;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.structured.parts.leaves.special.Keyword;


public class KeywordExpectation extends LeafExpectation {
    public KeywordExpectation(ReadableText value) {
        super(Keyword.class, value, null);
    }
}
