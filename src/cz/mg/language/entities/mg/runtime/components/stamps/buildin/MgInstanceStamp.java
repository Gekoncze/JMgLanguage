package cz.mg.language.entities.mg.runtime.components.stamps.buildin;

import cz.mg.collections.text.ReadonlyText;


public class MgInstanceStamp extends MgBuildinStamp {
    public static final MgInstanceStamp INSTANCE = new MgInstanceStamp();

    public static MgInstanceStamp getInstance() {
        return INSTANCE;
    }

    static {
        MgBuildinStamp.ALL.addLast(getInstance());
    }

    private MgInstanceStamp() {
        super(new ReadonlyText("instance"));
    }
}
