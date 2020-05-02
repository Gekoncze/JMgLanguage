package cz.mg.language.entities.logic.mg.definitions;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.logic.mg.MgLocable;


public class MgStamp extends MgDefinition implements MgLocable {
    public static final MgStamp PUBLIC = new MgStamp(new ReadonlyText("public"));
    public static final MgStamp PRIVATE = new MgStamp(new ReadonlyText("private"));

    @Value
    private final ReadableText name;

    public MgStamp(ReadableText name) {
        this.name = name;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
