package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.components.variables.MgFunctionVariable;


public class MgInterface implements MgComponent {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgFunctionVariable> input = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgFunctionVariable> output = new ArrayList<>();

    public MgInterface(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    @Override
    public ArrayList<MgStamp> getStamps() {
        return stamps;
    }

    public ArrayList<MgFunctionVariable> getInput() {
        return input;
    }

    public ArrayList<MgFunctionVariable> getOutput() {
        return output;
    }
}
