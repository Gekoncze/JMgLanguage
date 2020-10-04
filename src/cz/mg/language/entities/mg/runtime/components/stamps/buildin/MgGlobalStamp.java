package cz.mg.language.entities.mg.runtime.components.stamps.buildin;

import cz.mg.collections.text.ReadonlyText;


public class MgGlobalStamp extends MgBuildinStamp {
    public static final MgGlobalStamp INSTANCE = new MgGlobalStamp();

    public static MgGlobalStamp getInstance() {
        return INSTANCE;
    }

    static {
        MgBuildinStamp.ALL.addLast(getInstance());
    }

    private MgGlobalStamp() {
        super(new ReadonlyText("global"));
    }
}
