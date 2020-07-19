package cz.mg.language.entities.mg.runtime.instructions.sequential.set;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;


public class MgSetGlobalToLocalInstruction extends MgSequentialInstruction {
    @Value
    private final MgGlobalVariable globalVariable;

    @Value
    private final int targetIndex;

    public MgSetGlobalToLocalInstruction(MgGlobalVariable globalVariable, int targetIndex) {
        this.globalVariable = globalVariable;
        this.targetIndex = targetIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        thread.getCurrentFunctionObject().getObjects().set(globalVariable.getObject(), targetIndex);
        return getNextInstruction();
    }
}
