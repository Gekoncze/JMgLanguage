package cz.mg.language.entities.runtime.mg.instructions;

import cz.mg.language.entities.runtime.mg.MgEntityR;
import cz.mg.language.entities.runtime.mg.architecture.MgThreadR;


public abstract class MgInstructionR extends MgEntityR {
    public abstract MgInstructionR run(MgThreadR thread);
}
