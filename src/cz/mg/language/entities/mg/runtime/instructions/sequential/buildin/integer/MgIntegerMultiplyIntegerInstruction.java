package cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.integer;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.sequential.buildin.MgBuildinInstruction;
import cz.mg.language.entities.mg.runtime.atoms.MgIntegerObject;


public class MgIntegerMultiplyIntegerInstruction extends MgBuildinInstruction {
    @Value
    private final int sourceLeftIndex;

    @Value
    private final int sourceRightIndex;

    @Value
    private final int targetIndex;

    public MgIntegerMultiplyIntegerInstruction(int sourceLeftIndex, int sourceRightIndex, int targetIndex) {
        this.sourceLeftIndex = sourceLeftIndex;
        this.sourceRightIndex = sourceRightIndex;
        this.targetIndex = targetIndex;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgIntegerObject sourceLeft = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceLeftIndex);
        MgIntegerObject sourceRight = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceRightIndex);
        MgIntegerObject target = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(sourceLeft.getValue() * sourceRight.getValue());
        return getNextInstruction();
    }
}
