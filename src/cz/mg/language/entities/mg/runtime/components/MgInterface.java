package cz.mg.language.entities.mg.runtime.components;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.entities.mg.runtime.parts.MgLocalVariable;
import cz.mg.language.entities.mg.runtime.roles.MgComponent;


public class MgInterface implements MgComponent {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Part
    private ArrayList<@Link MgStamp> stamps = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgLocalVariable> input = new ArrayList<>();

    @Mandatory @Part
    private final ArrayList<MgLocalVariable> output = new ArrayList<>();

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

    public ArrayList<MgLocalVariable> getInput() {
        return input;
    }

    public ArrayList<MgLocalVariable> getOutput() {
        return output;
    }
}
