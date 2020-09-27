package cz.mg.language.entities.mg.runtime.buildin.types;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.buildin.atoms.MgAtom;
import cz.mg.language.entities.mg.runtime.buildin.atoms.MgTextObject;


public class MgTextType extends MgAtomType {
    public static final MgTextType INSTANCE = new MgTextType();

    public static MgTextType getInstance(){
        return INSTANCE;
    }

    private MgTextType() {
        super(new ReadonlyText("Text"));
    }

    @Override
    public MgAtom create(ReadableText text) {
        return new MgTextObject(text.toString());
    }
}
