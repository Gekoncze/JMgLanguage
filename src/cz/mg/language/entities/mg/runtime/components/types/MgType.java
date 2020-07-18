package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.components.MgComponent;


public class MgType extends MgComponent {
    public MgType(String name) {
        this(null, new ReadonlyText(name));
    }

    public MgType(MgType type, ReadableText name) {
        super(type, name);
    }
}
