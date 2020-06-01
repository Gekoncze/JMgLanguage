package cz.mg.language.tasks.mg.builders.pattern.part;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.part.expectations.*;


public class Expectations {
    public static final Expectation NAME(PartFieldProcessor fieldProcessor){
        return new NameExpectation(fieldProcessor);
    }

    public static final Expectation KEYWORD(String name){
        return new KeywordExpectation(new ReadonlyText(name));
    }

    public static final Expectation PATH(PartFieldProcessor fieldProcessor){
        return new PathExpectation(fieldProcessor);
    }

    public static final Expectation LIST(PartFieldProcessor fieldProcessor){
        return new ListExpectation(fieldProcessor);
    }

    public static final Expectation DECLARATION(PartFieldProcessor fieldProcessor){
        return new DeclarationExpectation(fieldProcessor);
    }
}
