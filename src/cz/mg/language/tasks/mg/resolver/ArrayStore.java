package cz.mg.language.tasks.mg.resolver;

import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.objects.MgObject;


public interface ArrayStore<O extends MgObject> {
    void put(Array<O> objects);
}
