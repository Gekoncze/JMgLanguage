package cz.mg.language.entities.mg.runtime.other.instructions.test;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgThreadR;
import cz.mg.language.entities.mg.runtime.other.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.objects.buildin.MgIntegerObject;


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
    public MgInstruction run(MgThreadR thread) {
        MgIntegerObject integer = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        System.out.println(integer.getValue());
        return nextInstruction;
    }
}
