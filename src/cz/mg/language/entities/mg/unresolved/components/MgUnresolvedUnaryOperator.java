package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.Operators;


public class MgUnresolvedUnaryOperator extends MgUnresolvedOperator {
    public MgUnresolvedUnaryOperator(ReadableText name) {
        super(name);
        setPriority(Operators.PRIORITIES_UNARY.get(name, 0));
    }
}
