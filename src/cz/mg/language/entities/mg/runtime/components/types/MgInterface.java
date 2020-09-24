package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.list.ArrayList;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.storage.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.variables.MgLocalVariable;


public class MgInterface extends MgType {
    private static final MgType TYPE = new MgType("Interface");

    @Mandatory @Part
    private ArrayList<MgLocalVariable> input = new ArrayList<>();

    @Mandatory @Part
    private ArrayList<MgLocalVariable> output = new ArrayList<>();

    protected MgInterface(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgInterface(ReadableText name) {
        super(TYPE, name);
    }

    public ArrayList<MgLocalVariable> getInput() {
        return input;
    }

    public ArrayList<MgLocalVariable> getOutput() {
        return output;
    }
}
