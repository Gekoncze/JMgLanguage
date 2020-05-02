package cz.mg.language.entities.logic.mg.instructions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.logic.mg.architecture.MgThread;


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
    public MgInstruction run(MgThread thread) {
        thread.getFunctionObjects().removeLast();
        return nextInstruction;
    }
}
