package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalVariable extends MgLogicalComponent {
    @Value
    private final ReadableText name;

    @Value
    private ReadableText type;

    @Value
    private Storage storage;

    @Value
    private Requirement requirement;

    @Value
    private final List<ReadableText> stamps = new List<>();

    public MgLogicalVariable(ReadableText name, ReadableText type, Storage storage, Requirement requirement) {
        this.name = name;
        this.type = type;
        this.storage = storage;
        this.requirement = requirement;
    }

    @Override
    public ReadableText getName() {
        return name;
    }

    public ReadableText getType() {
        return type;
    }

    public void setType(ReadableText type) {
        this.type = type;
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

    @Override
    public List<ReadableText> getStamps() {
        return stamps;
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
