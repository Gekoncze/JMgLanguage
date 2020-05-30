package cz.mg.language.entities.c.logical.elements.statements.declarations;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;


public class CStructureForwardDeclaration extends CForwardDeclaration implements Named {
    @Value
    private final ReadableText name;

    public CStructureForwardDeclaration(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
