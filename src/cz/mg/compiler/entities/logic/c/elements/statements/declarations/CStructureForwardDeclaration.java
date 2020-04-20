package cz.mg.compiler.entities.logic.c.elements.statements.declarations;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.Named;
import cz.mg.compiler.annotations.entity.Value;


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
