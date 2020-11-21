package cz.mg.language.entities.mg.unresolved.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.text.ReadableText;


public abstract class MgUnresolvedOperator extends MgUnresolvedFunction {
    @Mandatory @Value
    private int priority;

    public MgUnresolvedOperator(ReadableText name) {
        super(name);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
