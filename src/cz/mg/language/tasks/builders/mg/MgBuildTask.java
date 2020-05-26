package cz.mg.language.tasks.builders.mg;

import cz.mg.collections.ReadableCollection;
import cz.mg.language.LanguageException;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.Task;
import cz.mg.language.tasks.builders.mg.pattern.Patterns;


public abstract class MgBuildTask extends Task {
    public MgBuildTask() {
    }

    protected void match(Patterns patterns, ReadableCollection<Part> parts){
        if(!patterns.match(parts)){
            throw new LanguageException("Unrecognized pattern.");
        }
    }
}
