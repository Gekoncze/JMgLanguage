package cz.mg.language.entities.mg.runtime.other.instructions.buildin.integer;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgThreadR;
import cz.mg.language.entities.mg.runtime.other.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.other.instructions.buildin.MgBuildinInstruction;
import cz.mg.language.entities.mg.runtime.objects.buildin.MgIntegerObject;


public class MgIntegerMinusIntegerInstruction extends MgBuildinInstruction {
    @Value
    private final int sourceLeftIndex;

    @Value
    private final int sourceRightIndex;

    @Value
    private final int targetIndex;

    @Link
    private MgInstruction nextInstruction = null;

    public MgIntegerMinusIntegerInstruction(int sourceLeftIndex, int sourceRightIndex, int targetIndex) {
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
    public MgInstruction run(MgThreadR thread) {
        MgIntegerObject sourceLeft = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceLeftIndex);
        MgIntegerObject sourceRight = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceRightIndex);
        MgIntegerObject target = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(sourceLeft.getValue() - sourceRight.getValue());
        return nextInstruction;
    }
}
