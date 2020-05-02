package cz.mg.language.entities.text;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.Entity;


public abstract class TextEntity extends Entity {
    public abstract ReadableText toText();
}
