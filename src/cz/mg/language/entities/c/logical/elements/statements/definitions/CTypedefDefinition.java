package cz.mg.language.entities.c.logical.elements.statements.definitions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.storage.Value;


public class CTypedefDefinition extends CDefinition implements Named {
    @Value
    private final ReadableText oldName;

    @Value
    private final ReadableText name;

    public CTypedefDefinition(ReadableText oldName, ReadableText name) {
        this.oldName = oldName;
        this.name = name;
    }

    public ReadableText getOldName() {
        return oldName;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
