package cz.mg.language.entities.mg.runtime.buildin.types;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.buildin.atoms.MgAtom;
import cz.mg.language.entities.mg.runtime.buildin.atoms.MgIntObject;


public class MgIntType extends MgAtomType {
    public static final MgIntType INSTANCE = new MgIntType();

    public static MgIntType getInstance(){
        return INSTANCE;
    }

    private MgIntType() {
        super(new ReadonlyText("Int"));
    }

    @Override
    public MgAtom create(ReadableText text) {
        return new MgIntObject(Integer.parseInt(text.toString()));
    }
}
