package cz.mg.language.entities.mg.runtime.instructions.test;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThreadR;
import cz.mg.language.entities.mg.runtime.instructions.MgInstructionR;
import cz.mg.language.entities.mg.runtime.objects.elementary.MgIntegerObjectR;


public class MgPrintIntegerInstructionR extends MgTestInstructionR {
    @Value
    private final int sourceIndex;

    @Link
    private MgInstructionR nextInstruction = null;

    public MgPrintIntegerInstructionR(int sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    public MgInstructionR getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstructionR nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstructionR run(MgThreadR thread) {
        MgIntegerObjectR integer = (MgIntegerObjectR) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        System.out.println(integer.getValue());
        return nextInstruction;
    }
}
