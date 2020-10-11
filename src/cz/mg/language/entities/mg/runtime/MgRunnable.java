package cz.mg.language.entities.mg.runtime;

import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstance;


public interface MgRunnable extends MgObject {
    void run(MgFunctionInstance functionInstance);
}
