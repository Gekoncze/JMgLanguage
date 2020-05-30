package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.logical.parts.MgParameterL;


public class MgCollectionL extends MgClassL {
    @Part
    private final List<MgParameterL> parameters = new List<>();

    public MgCollectionL(ReadableText name) {
        super(name);
    }

    public List<MgParameterL> getParameters() {
        return parameters;
    }
}
