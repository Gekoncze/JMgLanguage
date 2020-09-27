package cz.mg.language.entities.mg.runtime.buildin.types;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.buildin.atoms.MgAtom;
import cz.mg.language.entities.mg.runtime.buildin.atoms.MgFloatObject;


public class MgFloatType extends MgAtomType {
    public static final MgFloatType INSTANCE = new MgFloatType();

    public static MgFloatType getInstance(){
        return INSTANCE;
    }

    private MgFloatType() {
        super(new ReadonlyText("Float"));
    }

    @Override
    public MgAtom create(ReadableText text) {
        return new MgFloatObject(Float.parseFloat(text.toString()));
    }
}
