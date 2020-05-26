package cz.mg.language.tasks.builders.mg.pattern;

import cz.mg.language.entities.text.structured.parts.Part;


public abstract class Expectation {
    public abstract boolean match(Part part);
}
