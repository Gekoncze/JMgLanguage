package cz.mg.language.entities.mg.runtime.atoms;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgTextObject extends MgAtom {
    public static final MgType TYPE = new MgType("Text");

    @Value
    private String value;

    public MgTextObject(String value) {
        super(TYPE);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public MgTextObject copy() {
        return new MgTextObject(value);
    }
}
