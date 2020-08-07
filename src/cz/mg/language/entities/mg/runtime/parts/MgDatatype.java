package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.language.annotations.entity.Link;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgDatatype extends MgPart {
    @Link
    private final MgType type;

    @Value
    private final Storage storage;

    @Value
    private final Requirement requirement;

    public MgDatatype(MgType type, Storage storage, Requirement requirement) {
        this.type = type;
        this.storage = storage;
        this.requirement = requirement;
    }

    public MgType getType() {
        return type;
    }

    public Storage getStorage() {
        return storage;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public enum Storage {
        DIRECT,
        INDIRECT
    }

    public enum Requirement {
        OPTIONAL,
        MANDATORY
    }
}
