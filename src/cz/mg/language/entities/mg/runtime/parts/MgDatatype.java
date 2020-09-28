package cz.mg.language.entities.mg.runtime.parts;

import cz.mg.language.annotations.storage.Link;
import cz.mg.language.annotations.storage.Value;
import cz.mg.language.annotations.requirement.Mandatory;
import cz.mg.language.entities.mg.runtime.components.types.MgType;


public class MgDatatype implements MgPart {
    @Mandatory @Link
    private final MgType type;

    @Mandatory @Value
    private final Storage storage;

    @Mandatory @Value
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
        INDIRECT,
        OTHER
    }

    public enum Requirement {
        OPTIONAL,
        MANDATORY
    }
}
