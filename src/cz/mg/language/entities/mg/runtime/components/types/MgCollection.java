package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.parts.MgParameter;


public class MgCollection extends MgClass {
    private static final MgType TYPE = new MgType("Collection");

    @Part
    private ReadableArray<MgParameter> parameters;

    protected MgCollection(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgCollection(ReadableText name) {
        super(TYPE, name);
    }

    public ReadableArray<MgParameter> getParameters() {
        return parameters;
    }

    public void setParameters(ReadableArray<MgParameter> parameters) {
        this.parameters = parameters;
    }
}
