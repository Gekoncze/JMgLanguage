package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgStamp extends MgComponent {
    private static final MgType TYPE = new MgType("Stamp");

    protected MgStamp(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgStamp(ReadableText name) {
        super(TYPE, name);
    }
}
