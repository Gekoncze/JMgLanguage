package cz.mg.language.entities.logic.mg.definitions.unresolved;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.logic.mg.definitions.MgFunction;
import cz.mg.language.entities.logic.mg.other.MgOperator;


public class MgUnresolvedFunction extends MgFunction {
    public MgUnresolvedFunction(ReadableText name, MgOperator operator) {
        super(name, operator);
    }

    public MgUnresolvedFunction(ReadableText name) {
        super(name);
    }
}
