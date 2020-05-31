package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.MgLogicalParameter;


public class MgLogicalCollection extends MgLogicalClass {
    @Part
    private final List<MgLogicalParameter> parameters = new List<>();

    public MgLogicalCollection() {
    }

    public MgLogicalCollection(ReadableText name) {
        super(name);
    }

    public List<MgLogicalParameter> getParameters() {
        return parameters;
    }
}
