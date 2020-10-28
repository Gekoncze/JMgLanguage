package cz.mg.language.entities.mg.runtime.components.types.functions;

import cz.mg.collections.text.ReadableText;


public abstract class MgUnaryOperator extends MgOperator {
    public MgUnaryOperator(ReadableText name, int priority) {
        super(name, priority);
    }
}
