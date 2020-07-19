package cz.mg.language.entities.mg.runtime.instructions.sequential.set;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;


public class MgSetGlobalToGlobalInstruction extends MgSequentialInstruction {
    @Value
    private final MgGlobalVariable sourceGlobalVariable;

    @Value
    private final MgGlobalVariable targetGlobalVariable;

    public MgSetGlobalToGlobalInstruction(MgGlobalVariable sourceGlobalVariable, MgGlobalVariable targetGlobalVariable) {
        this.sourceGlobalVariable = sourceGlobalVariable;
        this.targetGlobalVariable = targetGlobalVariable;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        targetGlobalVariable.setObject(sourceGlobalVariable.getObject());
        return getNextInstruction();
    }
}
