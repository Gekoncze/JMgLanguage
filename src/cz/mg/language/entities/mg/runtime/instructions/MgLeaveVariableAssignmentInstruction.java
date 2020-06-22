package cz.mg.language.entities.mg.runtime.instructions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;


public class MgLeaveVariableAssignmentInstruction extends MgInstruction {
    @Value
    private final int sourceVariableIndex;

    @Value
    private final int targetVariableIndex;

    @Link
    private MgInstruction nextInstruction = null;

    public MgLeaveVariableAssignmentInstruction(int sourceVariableIndex, int targetVariableIndex) {
        this.sourceVariableIndex = sourceVariableIndex;
        this.targetVariableIndex = targetVariableIndex;
    }

    public MgInstruction getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        thread.getFunctionObjects().getLastItem().getPrevious().getObjects().set(thread.getCurrentFunctionObject().getObjects().get(sourceVariableIndex), targetVariableIndex);
        return nextInstruction;
    }
}
