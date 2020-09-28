package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.entities.mg.runtime.parts.MgParameter;


public class MgCollection extends MgClass {
    @Mandatory @Part
    private final ArrayList<MgParameter> parameters = new ArrayList<>();

    public MgCollection(ReadableText name) {
        super(name);
    }

    public ReadableArray<MgParameter> getParameters() {
        return parameters;
    }
}
