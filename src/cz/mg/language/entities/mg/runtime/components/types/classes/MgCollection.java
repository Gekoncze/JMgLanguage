package cz.mg.language.entities.mg.runtime.components.types.classes;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
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
