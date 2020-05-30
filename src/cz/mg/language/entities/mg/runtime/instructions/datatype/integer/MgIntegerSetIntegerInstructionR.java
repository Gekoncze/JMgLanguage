package cz.mg.language.entities.mg.runtime.instructions.datatype.integer;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.architecture.MgThreadR;
import cz.mg.language.entities.mg.runtime.instructions.MgInstructionR;
import cz.mg.language.entities.mg.runtime.instructions.datatype.MgDatatypeInstructionR;
import cz.mg.language.entities.mg.runtime.objects.elementary.MgIntegerObjectR;


public class MgIntegerSetIntegerInstructionR extends MgDatatypeInstructionR {
    @Value
    private final int sourceIndex;

    @Value
    private final int targetIndex;

    @Link
    private MgInstructionR nextInstruction = null;

    public MgIntegerSetIntegerInstructionR(int sourceIndex, int targetIndex) {
        this.sourceIndex = sourceIndex;
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
        MgIntegerObjectR source = (MgIntegerObjectR) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgIntegerObjectR target = (MgIntegerObjectR) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(source.getValue());
        return nextInstruction;
    }
}
