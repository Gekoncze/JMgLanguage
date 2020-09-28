package cz.mg.language.entities.mg.runtime.components.stamps.buildin;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.runtime.components.stamps.MgStamp;


public abstract class MgBuildinStamp extends MgStamp {
    public static final List<MgBuildinStamp> ALL = new List<>();

    protected MgBuildinStamp(ReadableText name) {
        super(name);
    }
}
