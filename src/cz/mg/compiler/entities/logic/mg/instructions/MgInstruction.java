package cz.mg.compiler.entities.logic.mg.instructions;

import cz.mg.compiler.entities.logic.mg.MgEntity;
import cz.mg.compiler.entities.logic.mg.architecture.MgThread;


public abstract class MgInstruction extends MgEntity {
    public abstract MgInstruction run(MgThread thread);
}
