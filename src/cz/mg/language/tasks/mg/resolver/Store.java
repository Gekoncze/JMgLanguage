package cz.mg.language.tasks.mg.resolver;

import cz.mg.language.entities.mg.runtime.objects.MgObject;


public interface Store<O extends MgObject> {
    void put(O object);
}
