package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.Operators;


public class MgUnresolvedBinaryOperator extends MgUnresolvedOperator {
    public MgUnresolvedBinaryOperator(ReadableText name) {
        super(name);
        setPriority(Operators.PRIORITIES_BINARY.get(name, 0));
    }
}
