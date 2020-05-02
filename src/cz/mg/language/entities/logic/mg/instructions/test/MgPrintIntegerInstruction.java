package cz.mg.language.entities.logic.mg.instructions.test;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.architecture.MgThread;
import cz.mg.language.entities.logic.mg.instructions.MgInstruction;
import cz.mg.language.entities.logic.mg.objects.elementary.MgIntegerObject;


public class MgPrintIntegerInstruction extends MgTestInstruction {
    @Value
    private final int sourceIndex;

    @Link
    private MgInstruction nextInstruction = null;

    public MgPrintIntegerInstruction(int sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    public MgInstruction getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        MgIntegerObject integer = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        System.out.println(integer.getValue());
        return nextInstruction;
    }
}
