package cz.mg.language.entities.mg.runtime.components.types.functions;

import cz.mg.collections.text.ReadableText;


public class MgBinaryOperator extends MgOperator {
    public MgBinaryOperator(ReadableText name, int priority) {
        super(name, priority);
    }
}
