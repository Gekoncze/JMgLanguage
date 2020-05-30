package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.other.MgTypeR;


public class MgVariableR extends MgComponentR implements Named {
    @Value
    private final ReadableText name;

    @Value
    private final MgTypeR type;

    public MgVariableR(ReadableText name, MgTypeR type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgTypeR getType() {
        return type;
    }
}
