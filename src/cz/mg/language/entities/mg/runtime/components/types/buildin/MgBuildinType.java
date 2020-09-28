package cz.mg.language.entities.mg.runtime.components.types.buildin;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public abstract class MgBuildinType extends MgType {
    public static final List<MgBuildinType> ALL = new List<>();

    protected MgBuildinType(ReadableText name) {
        super(name);
    }
}
