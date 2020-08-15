package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.Array;
import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.MgVariable;


public class MgInterface extends MgType {
    private static final MgType TYPE = new MgType("Interface");

    @Mandatory @Part
    private ReadableArray<MgVariable> input = new Array<>();

    @Mandatory @Part
    private ReadableArray<MgVariable> output = new Array<>();

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

    public void setInput(ReadableArray<MgVariable> input) {
        if(input == null) throw new IllegalArgumentException();
        this.input = input;
    }

    public void setOutput(ReadableArray<MgVariable> output) {
        if(output == null) throw new IllegalArgumentException();
        this.output = output;
    }
}
