package cz.mg.language.entities.logic.mg.instructions;

import cz.mg.language.entities.logic.mg.MgLogicalEntity;
import cz.mg.language.entities.logic.mg.architecture.MgThread;


public abstract class MgInstruction extends MgLogicalEntity {
    public abstract MgInstruction run(MgThread thread);
}
