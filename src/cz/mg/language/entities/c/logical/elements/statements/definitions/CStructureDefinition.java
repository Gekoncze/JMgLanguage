package cz.mg.language.entities.c.logical.elements.statements.definitions;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.c.logical.parts.CModifier;
import cz.mg.language.entities.c.logical.parts.CVariable;


public class CStructureDefinition extends CDefinition implements Named {
    @Value
    private final ReadableText name;

    @Part
    private final List<CVariable> variables = new List<>();

    @Part
    private final List<CModifier> modifiers = new List<>();

    public CStructureDefinition(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public List<CVariable> getVariables() {
        return variables;
    }

    public List<CModifier> getModifiers() {
        return modifiers;
    }
}
