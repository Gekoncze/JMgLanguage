package cz.mg.language.entities.mg.logical.components;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.text.ReadableText;


public abstract class MgLogicalOperator extends MgLogicalFunction {
    @Mandatory @Value
    private int priority;

    public MgLogicalOperator(ReadableText name) {
        super(name);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
