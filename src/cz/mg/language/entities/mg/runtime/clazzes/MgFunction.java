package cz.mg.language.entities.mg.runtime.clazzes;

import cz.mg.collections.array.Array;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.other.MgStamp;
import cz.mg.language.entities.mg.runtime.other.instructions.MgInstruction;


public class MgFunction extends MgClazz {
    @Link
    private final Array<MgVariable> input;

    @Link
    private final Array<MgVariable> output;

    @Link
    private final Array<MgVariable> local;

    @Part
    private final Array<MgInstruction> instructions;

    @Value
    private final ReadableText operator;

    protected MgFunction(MgClazz type, ReadableText name, Array<MgVariable> input, Array<MgVariable> output, Array<MgVariable> local, Array<MgInstruction> instructions, ReadableText operator, Array<MgStamp> stamps) {
        super(type, name, new Array<>(0), join(input, output, local), new Array<>(0), stamps);
        this.input = input;
        this.output = output;
        this.local = local;
        this.instructions = instructions;
        this.operator = operator;
    }

    public MgFunction(ReadableText name, Array<MgVariable> input, Array<MgVariable> output, Array<MgVariable> local, Array<MgInstruction> instructions, ReadableText operator, Array<MgStamp> stamps) {
        this(Clazzes.FUNCTION, name, input, output, local, instructions, operator, stamps);
    }

    public Array<MgVariable> getInput() {
        return input;
    }

    public Array<MgVariable> getOutput() {
        return output;
    }

    public Array<MgVariable> getLocal() {
        return local;
    }

    public Array<MgInstruction> getInstructions() {
        return instructions;
    }

    public ReadableText getOperator() {
        return operator;
    }

    private static Array<MgVariable> join(Array<MgVariable> input, Array<MgVariable> output, Array<MgVariable> local){
        Array<MgVariable> variables = new Array<>(input.count() + output.count() + local.count());
        int index = 0;
        for(MgVariable in : input){
            variables.set(in, index);
            index++;
        }
        for(MgVariable out : output){
            variables.set(out, index);
            index++;
        }
        for(MgVariable loc : local){
            variables.set(loc, index);
            index++;
        }
        return variables;
    }
}
