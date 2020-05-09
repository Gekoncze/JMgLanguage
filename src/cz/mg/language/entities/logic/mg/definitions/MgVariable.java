package cz.mg.language.entities.logic.mg.definitions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.other.MgType;


public class MgVariable extends MgDefinition implements Named {
    @Value
    private final ReadableText name;

    @Value
    private final MgType type;

    public MgVariable(ReadableText name, MgType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public MgType getType() {
        return type;
    }
}
