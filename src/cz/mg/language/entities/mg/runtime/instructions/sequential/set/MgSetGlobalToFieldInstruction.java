package cz.mg.language.entities.mg.runtime.instructions.sequential.set;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;


public class MgSetGlobalToFieldInstruction extends MgSequentialInstruction {
    @Value
    private final MgGlobalVariable globalVariable;

    @Value
    private final int targetIndex;

    @Value
    private final int targetFieldIndex;

    public MgSetGlobalToFieldInstruction(MgGlobalVariable globalVariable, int targetIndex, int targetFieldIndex) {
        this.globalVariable = globalVariable;
        this.targetIndex = targetIndex;
        this.targetFieldIndex = targetFieldIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgClassObject target = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.getObjects().set(globalVariable.getObject(), targetFieldIndex);
        return getNextInstruction();
    }
}
