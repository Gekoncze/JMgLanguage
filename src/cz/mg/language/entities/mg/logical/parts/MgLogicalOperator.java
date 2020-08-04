package cz.mg.language.entities.mg.logical.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalOperator extends MgLogicalPart implements Named {
    @Value
    private final ReadableText name;

    @Value
    private Type type;

    @Value
    private int priority;

    public MgLogicalOperator(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public enum Type {
        LEFT,
        RIGHT,
        BINARY
    }
}
