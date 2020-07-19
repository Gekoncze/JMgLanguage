package cz.mg.language.entities.mg.runtime.instructions.sequential.set;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgSetFieldToLocalInstruction extends MgSequentialInstruction {
    @Value
    private final int sourceIndex;

    @Value
    private final int sourceFieldIndex;

    @Value
    private final int targetIndex;

    public MgSetFieldToLocalInstruction(int sourceIndex, int sourceFieldIndex, int targetIndex) {
        this.sourceIndex = sourceIndex;
        this.sourceFieldIndex = sourceFieldIndex;
        this.targetIndex = targetIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgClassObject source = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgObject sourceMember = source.getObjects().get(sourceFieldIndex);
        thread.getCurrentFunctionObject().getObjects().set(sourceMember, targetIndex);
        return getNextInstruction();
    }
}
