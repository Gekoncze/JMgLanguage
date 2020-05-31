package cz.mg.language.tasks.mg.builders.pattern.part;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.tasks.mg.builders.field.FieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.part.expectations.KeywordExpectation;
import cz.mg.language.tasks.mg.builders.pattern.part.expectations.ListExpectation;
import cz.mg.language.tasks.mg.builders.pattern.part.expectations.NameExpectation;
import cz.mg.language.tasks.mg.builders.pattern.part.expectations.PathExpectation;


public class Expectations {
    public static final Expectation NAME(FieldProcessor fieldProcessor){
        return new NameExpectation(fieldProcessor);
    }

    public static final Expectation KEYWORD(String name){
        return new KeywordExpectation(new ReadonlyText(name));
    }

    public static final Expectation PATH(FieldProcessor fieldProcessor){
        return new PathExpectation(fieldProcessor);
    }

    public static final Expectation LIST(FieldProcessor fieldProcessor){
        return new ListExpectation(fieldProcessor);
    }
}
