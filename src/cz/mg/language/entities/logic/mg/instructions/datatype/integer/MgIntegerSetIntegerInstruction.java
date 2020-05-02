package cz.mg.language.entities.logic.mg.instructions.datatype.integer;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.architecture.MgThread;
import cz.mg.language.entities.logic.mg.instructions.MgInstruction;
import cz.mg.language.entities.logic.mg.instructions.datatype.MgDatatypeInstruction;
import cz.mg.language.entities.logic.mg.objects.elementary.MgIntegerObject;


public class MgIntegerSetIntegerInstruction extends MgDatatypeInstruction {
    @Value
    private final int sourceIndex;

    @Value
    private final int targetIndex;

    @Link
    private MgInstruction nextInstruction = null;

    public MgIntegerSetIntegerInstruction(int sourceIndex, int targetIndex) {
        this.sourceIndex = sourceIndex;
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
        MgIntegerObject source = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgIntegerObject target = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(source.getValue());
        return nextInstruction;
    }
}
