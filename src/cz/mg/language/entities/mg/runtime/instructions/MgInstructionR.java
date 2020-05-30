package cz.mg.language.entities.mg.runtime.instructions;

import cz.mg.language.entities.mg.runtime.MgRuntimeEntity;
import cz.mg.language.entities.mg.runtime.architecture.MgThreadR;


public abstract class MgInstructionR extends MgRuntimeEntity {
    public abstract MgInstructionR run(MgThreadR thread);
}
