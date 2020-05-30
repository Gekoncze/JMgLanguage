package cz.mg.language.tasks.builders.mg.pattern;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.tasks.builders.mg.field.FieldProcessor;
import cz.mg.language.tasks.builders.mg.pattern.expectations.KeywordExpectation;
import cz.mg.language.tasks.builders.mg.pattern.expectations.NameExpectation;
import cz.mg.language.tasks.builders.mg.pattern.expectations.PathExpectation;


public class Expectations {
    public static final Expectation NAME(FieldProcessor fieldProcessor){
        return new NameExpectation(fieldProcessor);
    }

    public static final Expectation KEYWORD(){
        return new KeywordExpectation(null);
    }

    public static final Expectation KEYWORD(String name){
        return new KeywordExpectation(new ReadonlyText(name));
    }

    public static final Expectation PATH(FieldProcessor fieldProcessor){
        return new PathExpectation(fieldProcessor);
    }
}
