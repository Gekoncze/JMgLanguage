package cz.mg.language.entities.mg.runtime.other.instructions;

import cz.mg.language.entities.mg.runtime.clazzes.architecture.MgThreadR;
import cz.mg.language.entities.mg.runtime.other.MgOther;


public abstract class MgInstruction extends MgOther {
    public MgInstruction() {
    }

    public abstract MgInstruction run(MgThreadR thread);
}
