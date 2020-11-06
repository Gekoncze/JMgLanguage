package cz.mg.language.tasks.mg.resolver.search;

import cz.mg.collections.Clump;
import cz.mg.collections.array.Array;
import cz.mg.language.tasks.mg.resolver.command.utilities.Usage;


public class EmptySource implements Source {
    private static final Array<Usage> EMPTY_ARRAY = new Array<>();
    private static final EmptySource INSTANCE = new EmptySource();

    public static EmptySource getInstance() {
        return INSTANCE;
    }

    private EmptySource() {
    }

    @Override
    public Clump<Usage> getComponents() {
        return EMPTY_ARRAY;
    }
}
