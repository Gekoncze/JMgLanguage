package cz.mg.language.entities.mg.runtime.components.types;

import cz.mg.collections.array.ReadableArray;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.components.MgVariable;


public class MgFunction extends MgInterface {
    private static final MgType TYPE = new MgType("Function");

    @Part
    private ReadableArray<MgVariable> local;

    @Part
    private ReadableArray<MgGlobalVariable> globalVariables;

    @Part
    private ReadableArray<MgInstruction> instructions;

    @Value
    private ReadableText operator;

    @Value
    private int priority;

    protected MgFunction(MgType type, ReadableText name) {
        super(type, name);
    }

    public MgFunction(ReadableText name) {
        super(TYPE, name);
    }

    public ReadableArray<MgVariable> getLocal() {
        return local;
    }

    public ReadableArray<MgGlobalVariable> getGlobalVariables() {
        return globalVariables;
    }

    public void setGlobalVariables(ReadableArray<MgGlobalVariable> globalVariables) {
        this.globalVariables = globalVariables;
    }

    public ReadableArray<MgInstruction> getInstructions() {
        return instructions;
    }

    public ReadableText getOperator() {
        return operator;
    }

    public int getPriority() {
        return priority;
    }

    public void setLocal(ReadableArray<MgVariable> local) {
        this.local = local;
    }

    public void setInstructions(ReadableArray<MgInstruction> instructions) {
        this.instructions = instructions;
    }

    public void setOperator(ReadableText operator) {
        this.operator = operator;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
