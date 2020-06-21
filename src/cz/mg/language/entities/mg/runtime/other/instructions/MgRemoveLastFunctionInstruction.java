package cz.mg.language.entities.mg.runtime.other.instructions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgThreadR;


public class MgRemoveLastFunctionInstruction extends MgInstruction {
    @Link
    private MgInstruction nextInstruction = null;

    public MgRemoveLastFunctionInstruction() {
    }

    public MgInstruction getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstruction run(MgThreadR thread) {
        thread.getFunctionObjects().removeLast();
        return nextInstruction;
    }
}
