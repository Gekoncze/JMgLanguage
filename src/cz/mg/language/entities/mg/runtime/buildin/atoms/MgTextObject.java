package cz.mg.language.entities.mg.runtime.buildin.atoms;

import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.buildin.types.MgTextType;


public class MgTextObject extends MgAtom {
    @Value
    private String value;

    public MgTextObject(String value) {
        super(MgTextType.getInstance());
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
