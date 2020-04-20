package cz.mg.compiler.entities.logic.mg.instructions;

import cz.mg.compiler.annotations.entity.Link;
import cz.mg.compiler.annotations.entity.Value;
import cz.mg.compiler.entities.logic.mg.architecture.MgThread;


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
        thread.getFunctionObjects().getLastItem().getPrevious().getObjects().set(targetVariableIndex, thread.getCurrentFunctionObject().getObjects().get(sourceVariableIndex));
        return nextInstruction;
    }
}
