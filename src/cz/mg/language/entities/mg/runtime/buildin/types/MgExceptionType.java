package cz.mg.language.entities.mg.runtime.buildin.types;

import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.components.MgClass;
import cz.mg.language.entities.mg.runtime.components.MgType;


public class MgExceptionType {
    private static final MgType INSTANCE = new MgClass(new ReadonlyText("Exception"));

    public static MgType getInstance() {
        return INSTANCE;
    }
}
