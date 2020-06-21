package cz.mg.language.entities.mg.runtime.other.instructions.buildin.integer;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgThreadR;
import cz.mg.language.entities.mg.runtime.other.instructions.MgInstruction;
import cz.mg.language.entities.mg.runtime.other.instructions.buildin.MgBuildinInstruction;
import cz.mg.language.entities.mg.runtime.objects.buildin.MgIntegerObject;


public class MgIntegerSetIntegerInstruction extends MgBuildinInstruction {
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
    public MgInstruction run(MgThreadR thread) {
        MgIntegerObject source = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(sourceIndex);
        MgIntegerObject target = (MgIntegerObject) thread.getCurrentFunctionObject().getObjects().get(targetIndex);
        target.setValue(source.getValue());
        return nextInstruction;
    }
}
