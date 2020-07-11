package cz.mg.language.entities.mg.runtime.instructions.sequential;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.entities.mg.runtime.instructions.MgInstruction;


public abstract class MgSequentialInstruction extends MgInstruction {
    @Link
    private MgInstruction nextInstruction;

    public MgSequentialInstruction() {
    }

    protected MgInstruction getNextInstruction() {
        return nextInstruction;
    }

    public void setNextInstruction(MgInstruction nextInstruction) {
        this.nextInstruction = nextInstruction;
    }
}
