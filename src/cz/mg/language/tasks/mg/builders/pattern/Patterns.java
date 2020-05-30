package cz.mg.language.tasks.mg.builders.pattern;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.entities.text.structured.parts.Part;


public class Patterns {
    @cz.mg.language.annotations.entity.Part
    private final List<Pattern> patterns;

    public Patterns(Pattern... patterns) {
        this.patterns = new List<>(patterns);
    }

    public boolean match(ReadableCollection<Part> parts){
        for(Pattern pattern : patterns){
            if(pattern.match(parts)) return true;
        }
        return false;
    }
}
