package cz.mg.language.entities.mg.runtime.instructions.sequential.set;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.components.MgGlobalVariable;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.MgSequentialInstruction;
import cz.mg.language.entities.mg.runtime.objects.MgClassObject;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public class MgSetFieldToGlobalInstruction extends MgSequentialInstruction {
    @Value
    private final int sourceIndex;

    @Value
    private final int sourceFieldIndex;

    @Value
    private final MgGlobalVariable globalVariable;

    public MgSetFieldToGlobalInstruction(int sourceIndex, int sourceFieldIndex, MgGlobalVariable globalVariable) {
        this.sourceIndex = sourceIndex;
        this.sourceFieldIndex = sourceFieldIndex;
        this.globalVariable = globalVariable;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgClassObject source = (MgClassObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgObject sourceMember = source.getObjects().get(sourceFieldIndex);
       globalVariable.setObject(sourceMember);
        return getNextInstruction();
    }
}
