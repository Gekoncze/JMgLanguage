package cz.mg.language.entities.runtime.mg.instructions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.runtime.mg.architecture.MgThreadR;


public class MgRemoveLastFunctionInstructionR extends MgInstructionR {
    @Link
    private MgInstructionR nextInstruction = null;

    public MgRemoveLastFunctionInstructionR() {
    }

    public MgInstructionR getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstructionR nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstructionR run(MgThreadR thread) {
        thread.getFunctionObjects().removeLast();
        return nextInstruction;
    }
}
