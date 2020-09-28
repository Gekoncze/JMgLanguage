package cz.mg.language.entities.mg.runtime.components.stamps.buildin;

import cz.mg.collections.text.ReadonlyText;


public class MgPublicStamp extends MgBuildinStamp {
    public static final MgPublicStamp INSTANCE = new MgPublicStamp();

    public static MgPublicStamp getInstance() {
        return INSTANCE;
    }

    static {
        MgBuildinStamp.ALL.addLast(getInstance());
    }

    private MgPublicStamp() {
        super(new ReadonlyText("public"));
    }
}
