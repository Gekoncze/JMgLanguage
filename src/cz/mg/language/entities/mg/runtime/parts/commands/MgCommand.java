package cz.mg.language.entities.mg.runtime.parts.commands;

import cz.mg.collections.array.ReadableArray;
import cz.mg.language.annotations.entity.Part;
import cz.mg.language.entities.mg.runtime.MgRunnable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.parts.MgPart;


public abstract class MgCommand extends MgPart implements MgRunnable {
    @Part
    private ReadableArray<MgInstruction> instructions;

    public MgCommand() {
    }

    public ReadableArray<MgInstruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ReadableArray<MgInstruction> instructions) {
        this.instructions = instructions;
    }
}
