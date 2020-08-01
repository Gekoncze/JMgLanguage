package cz.mg.language.entities.mg.runtime.instructions;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.architecture.MgThread;


public class MgJumpInstruction extends MgInstruction {
    @Link
    private MgInstruction nextInstruction;

    public MgJumpInstruction() {
    }

    protected MgInstruction getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }

    @Override
    public MgInstruction run(MgThread thread) {
        return nextInstruction;
    }
}
