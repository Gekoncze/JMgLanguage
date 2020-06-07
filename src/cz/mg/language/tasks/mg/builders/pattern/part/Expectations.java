package cz.mg.language.tasks.mg.builders.pattern.part;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.entities.text.structured.parts.groups.Group;
import cz.mg.language.entities.text.structured.parts.groups.chains.ListChain;
import cz.mg.language.entities.text.structured.parts.groups.chains.PathChain;
import cz.mg.language.tasks.mg.builders.field.PartFieldProcessor;
import cz.mg.language.tasks.mg.builders.pattern.part.expectations.*;


public class Expectations {
    public static final Expectation PART(PartFieldProcessor fieldProcessor){
        return new ClassExpectation(Part.class, fieldProcessor);
    }

    public static final Expectation GROUP(PartFieldProcessor fieldProcessor){
        return new ClassExpectation(Group.class, fieldProcessor);
    }

    public static final Expectation PATH(PartFieldProcessor fieldProcessor){
        return new ClassExpectation(PathChain.class, fieldProcessor);
    }

    public static final Expectation LIST(PartFieldProcessor fieldProcessor){
        return new ClassExpectation(ListChain.class, fieldProcessor);
    }

    public static final Expectation NAME(PartFieldProcessor fieldProcessor){
        return new NameExpectation(fieldProcessor);
    }

    public static final Expectation KEYWORD(String name){
        return new KeywordExpectation(new ReadonlyText(name));
    }

    public static final Expectation DECLARATION(PartFieldProcessor fieldProcessor){
        return new DeclarationExpectation(fieldProcessor);
    }
}
