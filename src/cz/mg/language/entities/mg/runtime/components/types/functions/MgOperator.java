package cz.mg.language.entities.mg.runtime.components.types.functions;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.text.ReadableText;


public abstract class MgOperator extends MgGlobalFunction {
    @Mandatory @Value
    private final int priority;

    public MgOperator(ReadableText name, int priority) {
        super(name);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
