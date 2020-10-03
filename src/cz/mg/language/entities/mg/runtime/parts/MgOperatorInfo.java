package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Value;


public class MgOperatorInfo extends MgPart {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Value
    private final Type type;

    @Mandatory @Value
    private final int priority;

    public MgOperatorInfo(ReadableText name, Type type, int priority) {
        this.name = name;
        this.type = type;
        this.priority = priority;
    }

    public ReadableText getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public enum Type {
        LEFT,
        RIGHT,
        BINARY
    }
}
