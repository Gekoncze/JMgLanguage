package cz.mg.language.entities.mg.unresolved.parts;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.annotations.storage.Value;


public class MgUnresolvedDatatype extends MgUnresolvedPart implements Named {
    @Value
    private final ReadableText name;

    @Value
    private Storage storage;

    @Value
    private Requirement requirement;

    public MgUnresolvedDatatype(ReadableText name) {
        this.name = name;
    }

    public MgUnresolvedDatatype(ReadableText name, Storage storage, Requirement requirement) {
        this.name = name;
        this.storage = storage;
        this.requirement = requirement;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
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
