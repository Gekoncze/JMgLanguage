package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgVariable;


public class MgInterface extends MgType {
    private static final MgType TYPE = new MgType("Interface");

    @Mandatory @Part
    private ArrayList<MgVariable> input = new ArrayList<>();

    @Mandatory @Part
    private ArrayList<MgVariable> output = new ArrayList<>();

    protected MgInterface(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgInterface(ReadableText name) {
        super(TYPE, name);
    }

    public ReadableArray<MgVariable> getInput() {
        return input;
    }

    public ReadableArray<MgVariable> getOutput() {
        return output;
    }
}
