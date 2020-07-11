package cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.floatt;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.atoms.MgFloatObject;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.MgBuildinInstruction;


public class MgFloatNegateInstruction extends MgBuildinInstruction {
    @Value
    private final int sourceIndex;

    @Value
    private final int targetIndex;

    public MgFloatNegateInstruction(int sourceIndex, int targetIndex) {
        this.sourceIndex = sourceIndex;
        this.targetIndex = targetIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgFloatObject source = (MgFloatObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgFloatObject target = (MgFloatObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(-source.getValue());
        return getNextInstruction();
    }
}
