package cz.mg.language.entities.mg.runtime.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.other.MgStamp;
import cz.mg.language.entities.mg.runtime.other.MgVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public class MgFunction extends MgType {
    private static final MgType TYPE = new MgType("Function");

    @Part
    private final ReadableArray<MgVariable> input;

    @Part
    private final ReadableArray<MgVariable> output;

    @Part
    private final ReadableArray<MgVariable> local;

    @Part
    private final ReadableArray<MgInstruction> instructions;

    @Value
    private final ReadableText operator;

    protected MgFunction(MgType type, ReadableText name, ReadableArray<MgVariable> input, ReadableArray<MgVariable> output, ReadableArray<MgVariable> local, ReadableArray<MgInstruction> instructions, ReadableText operator, ReadableArray<MgStamp> stamps) {
        super(type, name, stamps);
        this.input = input;
        this.output = output;
        this.local = local;
        this.instructions = instructions;
        this.operator = operator;
    }

    public MgFunction(ReadableText name, ReadableArray<MgVariable> input, ReadableArray<MgVariable> output, ReadableArray<MgVariable> local, ReadableArray<MgInstruction> instructions, ReadableText operator, ReadableArray<MgStamp> stamps) {
        this(TYPE, name, input, output, local, instructions, operator, stamps);
    }

    public ReadableArray<MgVariable> getInput() {
        return input;
    }

    public ReadableArray<MgVariable> getOutput() {
        return output;
    }

    public ReadableArray<MgVariable> getLocal() {
        return local;
    }

    public ReadableArray<MgInstruction> getInstructions() {
        return instructions;
    }

    public ReadableText getOperator() {
        return operator;
    }
}
