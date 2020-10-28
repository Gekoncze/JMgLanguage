package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.Operators;


public class MgLogicalUnaryOperator extends MgLogicalOperator {
    public MgLogicalUnaryOperator(ReadableText name) {
        super(name);
        setPriority(Operators.PRIORITIES_UNARY.get(name, 0));
    }
}
