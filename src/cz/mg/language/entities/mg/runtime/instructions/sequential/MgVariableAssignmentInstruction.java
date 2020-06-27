package cz.mg.language.entities.mg.runtime.instructions.sequential;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public class MgVariableAssignmentInstruction extends MgSequentialInstruction {
    @Value
    private final int sourceVariableIndex;

    @Value
    private final int targetVariableIndex;

    public MgVariableAssignmentInstruction(int sourceVariableIndex, int targetVariableIndex) {
        this.sourceVariableIndex = sourceVariableIndex;
        this.targetVariableIndex = targetVariableIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        thread.getCurrentFunctionObject().getObjects().set(thread.getCurrentFunctionObject().getObjects().get(sourceVariableIndex), targetVariableIndex);
        return getNextInstruction();
    }
}
