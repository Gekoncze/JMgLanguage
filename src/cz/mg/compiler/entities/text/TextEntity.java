package cz.mg.compiler.entities.text;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.entities.Entity;


public abstract class TextEntity extends Entity {
    public abstract ReadableText toText();
}
