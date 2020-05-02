package cz.mg.language.entities.logic.mg.definitions.unresolved;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.definitions.MgDefinition;


public class MgUnresolvedDefinition extends MgDefinition {
    @Value
    private final ReadableText name;

    public MgUnresolvedDefinition(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
