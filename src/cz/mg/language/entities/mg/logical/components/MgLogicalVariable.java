package cz.mg.language.entities.mg.logical.components;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.entity.Value;


public class MgLogicalVariable extends MgLogicalComponent {
    @Value
    private ReadableText type;

    @Value
    private Storage storage;

    public MgLogicalVariable() {
    }

    public MgLogicalVariable(ReadableText name, ReadableText type, Storage storage) {
        super(name);
        this.type = type;
        this.storage = storage;
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

    public enum Storage {
        VALUE,
        ADDRESS,
        NULLABLE_ADDRESS
    }
}
