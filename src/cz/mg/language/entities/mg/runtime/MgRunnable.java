package cz.mg.language.entities.mg.runtime;

import cz.mg.language.entities.mg.runtime.instances.MgFunctionInstanceImpl;


public interface MgRunnable extends MgObject {
    void run(MgFunctionInstanceImpl functionObject);
}
