package cz.mg.language.entities.mg.runtime.instructions.sequential;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgSetVariableToFieldInstruction extends MgSequentialInstruction {
    @Value
    private final int sourceIndex;

    @Value
    private final int targetIndex;

    @Value
    private final int targetFieldIndex;

    public MgSetVariableToFieldInstruction(int sourceIndex, int targetIndex, int targetFieldIndex) {
        this.sourceIndex = sourceIndex;
        this.targetIndex = targetIndex;
        this.targetFieldIndex = targetFieldIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgObject source = thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgClassObject target = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.getObjects().set(source, targetFieldIndex);
        return getNextInstruction();
    }
}
