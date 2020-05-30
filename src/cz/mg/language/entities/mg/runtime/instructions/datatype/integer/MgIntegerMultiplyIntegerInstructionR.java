package cz.mg.language.entities.mg.runtime.instructions.datatype.integer;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThreadR;
import cz.mg.language.entities.mg.runtime.instructions.MgInstructionR;
import cz.mg.language.entities.mg.runtime.instructions.datatype.MgDatatypeInstructionR;
import cz.mg.language.entities.mg.runtime.objects.elementary.MgIntegerObjectR;


public class MgIntegerMultiplyIntegerInstructionR extends MgDatatypeInstructionR {
    @Value
    private final int sourceLeftIndex;

    @Value
    private final int sourceRightIndex;

    @Value
    private final int targetIndex;

    @Link
    private MgInstructionR nextInstruction = null;

    public MgIntegerMultiplyIntegerInstructionR(int sourceLeftIndex, int sourceRightIndex, int targetIndex) {
        this.sourceLeftIndex = sourceLeftIndex;
        this.sourceRightIndex = sourceRightIndex;
        this.targetIndex = targetIndex;
    }

    public MgInstructionR getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstructionR nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstructionR run(MgThreadR thread) {
        MgIntegerObjectR sourceLeft = (MgIntegerObjectR) thread.getCurrentFunctionObject().getObjects().get(sourceLeftIndex);
        MgIntegerObjectR sourceRight = (MgIntegerObjectR) thread.getCurrentFunctionObject().getObjects().get(sourceRightIndex);
        MgIntegerObjectR target = (MgIntegerObjectR) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(sourceLeft.getValue() * sourceRight.getValue());
        return nextInstruction;
    }
}
