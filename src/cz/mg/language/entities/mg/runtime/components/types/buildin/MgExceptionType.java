package cz.mg.language.entities.mg.runtime.components.types.buildin;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.components.types.classes.MgClass;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgExceptionType {
    private static final MgType INSTANCE = new MgClass(new ReadonlyText("Exception"));

    public static MgType getInstance() {
        return INSTANCE;
    }
}
