package cz.mg.language.entities.mg.runtime.components.stamps.buildin;

import cz.mg.collections.text.ReadonlyText;


public class MgPrivateStamp extends MgBuildinStamp {
    public static final MgPrivateStamp INSTANCE = new MgPrivateStamp();

    public static MgPrivateStamp getInstance() {
        return INSTANCE;
    }

    static {
        MgBuildinStamp.ALL.addLast(getInstance());
    }

    private MgPrivateStamp() {
        super(new ReadonlyText("private"));
    }
}
