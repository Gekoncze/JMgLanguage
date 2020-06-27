package cz.mg.language.entities.mg.runtime.instructions.sequential;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;


public class MgSetFieldToFieldInstruction extends MgSequentialInstruction {
    @Value
    private final int sourceIndex;

    @Value
    private final int sourceFieldIndex;

    @Value
    private final int targetIndex;

    @Value
    private final int targetFieldIndex;

    public MgSetFieldToFieldInstruction(int sourceIndex, int sourceFieldIndex, int targetIndex, int targetFieldIndex) {
        this.sourceIndex = sourceIndex;
        this.sourceFieldIndex = sourceFieldIndex;
        this.targetIndex = targetIndex;
        this.targetFieldIndex = targetFieldIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgClassObject source = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgClassObject target = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.getObjects().set(source.getObjects().get(sourceFieldIndex), targetFieldIndex);
        return getNextInstruction();
    }
}
