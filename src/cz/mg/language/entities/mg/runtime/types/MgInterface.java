package cz.mg.language.entities.mg.runtime.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.other.MgVariable;


public class MgInterface extends MgType {
    private static final MgType TYPE = new MgType("Interface");

    @Part
    private ReadableArray<MgVariable> input;

    @Part
    private ReadableArray<MgVariable> output;

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
        this.input = input;
    }

    public void setOutput(ReadableArray<MgVariable> output) {
        this.output = output;
    }
}
