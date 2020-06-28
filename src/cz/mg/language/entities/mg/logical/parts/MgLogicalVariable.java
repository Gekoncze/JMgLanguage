package cz.mg.language.entities.mg.logical.parts;

import cz.mg.collections.list.List;
import cz.mg.collections.text.ReadableText;
import cz.mg.language.Named;
import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.mg.logical.Stampable;


public class MgLogicalVariable extends MgLogicalPart implements Named, Stampable {
    @Value
    private final ReadableText name;

    @Value
    private ReadableText type;

    @Value
    private Storage storage;

    @Value
    private final List<ReadableText> stamps = new List<>();

    public MgLogicalVariable(ReadableText name, ReadableText type, Storage storage) {
        this.name = name;
        this.type = type;
        this.storage = storage;
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

    @Override
    public List<ReadableText> getStamps() {
        return stamps;
    }

    public enum Storage {
        VALUE,
        ADDRESS,
        NULLABLE_ADDRESS
    }
}
