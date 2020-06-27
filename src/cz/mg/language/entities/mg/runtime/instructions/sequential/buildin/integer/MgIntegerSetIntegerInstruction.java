package cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.integer;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.atoms.MgIntegerObject;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.MgBuildinInstruction;


public class MgIntegerSetIntegerInstruction extends MgBuildinInstruction {
    @Value
    private final int sourceIndex;

    @Value
    private final int targetIndex;

    public MgIntegerSetIntegerInstruction(int sourceIndex, int targetIndex) {
        this.sourceIndex = sourceIndex;
        this.targetIndex = targetIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgIntegerObject source = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgIntegerObject target = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(source.getValue());
        return getNextInstruction();
    }
}
