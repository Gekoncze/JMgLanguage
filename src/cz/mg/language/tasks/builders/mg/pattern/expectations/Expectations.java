package cz.mg.language.tasks.builders.mg.pattern.expectations;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.tasks.builders.mg.pattern.Expectation;


public class Expectations {
    public static final Expectation NAME(){
        return new NameExpectation(null);
    }

    public static final Expectation NAME(String name){
        return new NameExpectation(new ReadonlyText(name));
    }

    public static final Expectation KEYWORD(){
        return new KeywordExpectation(null);
    }

    public static final Expectation KEYWORD(String name){
        return new KeywordExpectation(new ReadonlyText(name));
    }

    public static final Expectation PATH(){
        return new PathExpectation();
    }
}
