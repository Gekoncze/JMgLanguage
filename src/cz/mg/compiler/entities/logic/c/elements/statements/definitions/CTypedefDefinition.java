package cz.mg.compiler.entities.logic.c.elements.statements.definitions;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.Named;
import cz.mg.compiler.annotations.entity.Value;


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
