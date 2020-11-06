package cz.mg.language.tasks.mg.resolver.search;

import cz.mg.collections.Clump;
import cz.mg.collections.array.Array;
import cz.mg.language.entities.mg.runtime.components.MgComponent;


public class EmptySource implements Source {
    private static final Array<MgComponent> EMPTY_ARRAY = new Array<>();
    private static final EmptySource INSTANCE = new EmptySource();

    public static EmptySource getInstance() {
        return INSTANCE;
    }

    private EmptySource() {
    }

    @Override
    public Clump<? extends MgComponent> getComponents() {
        return EMPTY_ARRAY;
    }
}
