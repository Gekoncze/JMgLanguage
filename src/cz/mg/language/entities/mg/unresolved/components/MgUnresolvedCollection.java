package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.storage.Part;
import cz.mg.language.entities.mg.unresolved.parts.MgUnresolvedParameter;


public class MgUnresolvedCollection extends MgUnresolvedClass {
    @Part
    private final List<MgUnresolvedParameter> parameters = new List<>();

    public MgUnresolvedCollection() {
    }

    public MgUnresolvedCollection(ReadableText name) {
        super(name);
    }

    public List<MgUnresolvedParameter> getParameters() {
        return parameters;
    }
}
