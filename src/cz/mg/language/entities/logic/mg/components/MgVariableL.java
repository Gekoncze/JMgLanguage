package cz.mg.language.entities.logic.mg.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.logic.mg.parts.MgUsageL;


public class MgVariableL extends MgComponentL {
    @Link
    private final MgUsageL type;

    public MgVariableL(ReadableText name, MgUsageL type) {
        super(name);
        this.type = type;
    }

    public MgUsageL getType() {
        return type;
    }
}
