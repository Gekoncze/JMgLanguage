package cz.mg.compiler.entities.logic.c.elements.statements.definitions;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.Named;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.c.parts.CFunctionType;


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
