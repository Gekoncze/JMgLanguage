package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;


public class MgUnresolvedClass extends MgUnresolvedComponent {
    @Value
    private final List<ReadableText> baseClasses = new List<>();

    @Part
    private final List<MgUnresolvedVariable> variables = new List<>();

    @Part
    private final List<MgUnresolvedFunction> functions = new List<>();

    public MgUnresolvedClass() {
    }

    public MgUnresolvedClass(ReadableText name) {
        super(name);
    }

    public List<ReadableText> getBaseClasses() {
        return baseClasses;
    }

    public List<MgUnresolvedVariable> getVariables() {
        return variables;
    }

    public List<MgUnresolvedFunction> getFunctions() {
        return functions;
    }
}
