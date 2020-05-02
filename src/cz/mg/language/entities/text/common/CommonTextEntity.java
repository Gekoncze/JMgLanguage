package cz.mg.language.entities.text.common;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.text.TextEntity;


public abstract class CommonTextEntity extends TextEntity {
    public abstract ReadableText toText();
}
