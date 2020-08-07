package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgOperator extends MgPart {
    @Value
    private final ReadableText name;

    @Value
    private Type type;

    @Value
    private int priority;

    public MgOperator(ReadableText name) {
        this.name = name;
    }

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
