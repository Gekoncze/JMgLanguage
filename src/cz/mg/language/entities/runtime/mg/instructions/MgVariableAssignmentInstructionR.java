package cz.mg.language.entities.runtime.mg.instructions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.runtime.mg.architecture.MgThreadR;


public class MgVariableAssignmentInstructionR extends MgInstructionR {
    @Value
    private final int sourceVariableIndex;

    @Value
    private final int targetVariableIndex;

    @Link
    private MgInstructionR nextInstruction = null;

    public MgVariableAssignmentInstructionR(int sourceVariableIndex, int targetVariableIndex) {
        this.sourceVariableIndex = sourceVariableIndex;
        this.targetVariableIndex = targetVariableIndex;
    }

    public MgInstructionR getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstructionR nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstructionR run(MgThreadR thread) {
        thread.getCurrentFunctionObject().getObjects().set(targetVariableIndex, thread.getCurrentFunctionObject().getObjects().get(sourceVariableIndex));
        return nextInstruction;
    }
}
