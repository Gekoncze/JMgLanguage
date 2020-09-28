package cz.mg.language.entities.mg.runtime.components.types.buildin;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgFloatObject;


public class MgFloatType extends MgAtomType {
    public static final MgFloatType INSTANCE = new MgFloatType();

    public static MgFloatType getInstance(){
        return INSTANCE;
    }

    static {
        MgBuildinType.ALL.addLast(getInstance());
    }

    private MgFloatType() {
        super(new ReadonlyText("Float"));
    }

    @Override
    public MgAtom create(ReadableText text) {
        return new MgFloatObject(Float.parseFloat(text.toString()));
    }
}
