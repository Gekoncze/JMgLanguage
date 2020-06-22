package cz.mg.language.entities.mg.runtime.instructions.buildin.integer;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.instructions.buildin.MgBuildinInstruction;
import cz.mg.language.entities.mg.runtime.atoms.MgIntegerObject;


public class MgIntegerPlusIntegerInstruction extends MgBuildinInstruction {
    @Value
    private final int sourceLeftIndex;

    @Value
    private final int sourceRightIndex;

    @Value
    private final int targetIndex;

    @Link
    private MgInstruction nextInstruction = null;

    public MgIntegerPlusIntegerInstruction(int sourceLeftIndex, int sourceRightIndex, int targetIndex) {
        this.sourceLeftIndex = sourceLeftIndex;
        this.sourceRightIndex = sourceRightIndex;
        this.targetIndex = targetIndex;
    }

    public MgInstruction getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgIntegerObject sourceLeft = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceLeftIndex);
        MgIntegerObject sourceRight = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceRightIndex);
        MgIntegerObject target = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(sourceLeft.getValue() + sourceRight.getValue());
        return nextInstruction;
    }
}
