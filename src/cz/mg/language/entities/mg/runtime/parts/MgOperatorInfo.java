package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.annotations.storage.Value;


public class MgOperatorInfo extends MgPart {
    @Mandatory @Value
    private final ReadableText name;

    @Mandatory @Value
    private final MgOperatorInfo.Position position;

    @Mandatory @Value
    private final int priority;

    public MgOperatorInfo(ReadableText name, Position position, int priority) {
        this.name = name;
        this.position = position;
        this.priority = priority;
    }

    public ReadableText getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public int getPriority() {
        return priority;
    }

    public enum Position {
        LEFT,
        RIGHT,
        BINARY
    }
}
