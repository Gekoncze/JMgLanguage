package cz.mg.language.entities.mg.runtime.instructions.sequential;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgSetVariableToVariableInstruction extends MgSequentialInstruction {
    @Value
    private final int sourceVariableIndex;

    @Value
    private final int targetVariableIndex;

    public MgSetVariableToVariableInstruction(int sourceVariableIndex, int targetVariableIndex) {
        this.sourceVariableIndex = sourceVariableIndex;
        this.targetVariableIndex = targetVariableIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgObject source = thread.getCurrentFunctionObject().getObjects().get(sourceVariableIndex);
        thread.getCurrentFunctionObject().getObjects().set(source, targetVariableIndex);
        return getNextInstruction();
    }
}
