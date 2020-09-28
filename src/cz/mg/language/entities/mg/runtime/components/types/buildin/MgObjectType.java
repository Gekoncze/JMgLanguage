package cz.mg.language.entities.mg.runtime.components.types.buildin;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgObjectType extends MgType {
    private static final MgObjectType INSTANCE = new MgObjectType();

    public static MgObjectType getInstance() {
        return INSTANCE;
    }

    private MgObjectType() {
        super(new ReadonlyText("Object"));
    }

    @Override
    public boolean is(MgType baseType) {
        return baseType == this;
    }
}
