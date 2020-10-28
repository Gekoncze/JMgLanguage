package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.mg.Operators;


public class MgLogicalBinaryOperator extends MgLogicalOperator {
    public MgLogicalBinaryOperator(ReadableText name) {
        super(name);
        setPriority(Operators.PRIORITIES_BINARY.get(name, 0));
    }
}
