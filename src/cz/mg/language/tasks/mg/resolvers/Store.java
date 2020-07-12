package cz.mg.language.tasks.mg.resolvers;

import cz.mg.language.entities.mg.runtime.objects.MgObject;


public interface Store<O extends MgObject> {
    void put(O object);
}
