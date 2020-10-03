package cz.mg.language.entities.mg.runtime.components.stamps.buildin;

import cz.mg.collections.text.ReadonlyText;


public class MgTypeStamp extends MgBuildinStamp {
    public static final MgTypeStamp INSTANCE = new MgTypeStamp();

    public static MgTypeStamp getInstance() {
        return INSTANCE;
    }

    static {
        MgBuildinStamp.ALL.addLast(getInstance());
    }

    private MgTypeStamp() {
        super(new ReadonlyText("type"));
    }
}
