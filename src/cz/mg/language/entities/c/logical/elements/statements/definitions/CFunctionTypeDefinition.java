package cz.mg.language.entities.c.logical.elements.statements.definitions;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.c.logical.parts.CFunctionType;


public class CFunctionTypeDefinition extends CDefinition implements Named {
    @Value
    private final ReadableText name;

    @Part
    private final CFunctionType type;

    public CFunctionTypeDefinition(ReadableText name) {
        this(name, new CFunctionType());
    }

    public CFunctionTypeDefinition(ReadableText name, CFunctionType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public CFunctionType getType() {
        return type;
    }
}
