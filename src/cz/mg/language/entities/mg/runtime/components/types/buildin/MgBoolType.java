package cz.mg.language.entities.mg.runtime.components.types.buildin;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgAtom;
import cz.mg.language.entities.mg.runtime.instances.buildin.MgBoolObject;


public class MgBoolType extends MgAtomType {
    public static final MgBoolType INSTANCE = new MgBoolType();

    public static MgBoolType getInstance(){
        return INSTANCE;
    }

    static {
        MgBuildinType.ALL.addLast(getInstance());
    }

    private MgBoolType() {
        super(new ReadonlyText("Bool"));
    }

    @Override
    public MgAtom create(ReadableText text) {
        return new MgBoolObject(Boolean.parseBoolean(text.toString()));
    }
}
