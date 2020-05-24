package cz.mg.language.entities.text.structured.parts;

import cz.mg.language.annotations.entity.Value;
import cz.mg.language.entities.text.structured.parts.leaves.names.TypeName;
import cz.mg.language.entities.text.structured.parts.leaves.names.ObjectName;


public class Declaration extends Part {
    @Value
    private final Storage storage;

    @cz.mg.language.annotations.entity.Part
    private final TypeName typeName;

    @cz.mg.language.annotations.entity.Part
    private final ObjectName objectName;

    public Declaration(Storage storage, TypeName typeName, ObjectName objectName) {
        this.storage = storage;
        this.typeName = typeName;
        this.objectName = objectName;
    }

    public Storage getStorage() {
        return storage;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public ObjectName getObjectName() {
        return objectName;
    }

    public enum Storage {
        VALUE,
        ADDRESS,
        NULLABLE_ADDRESS
    }
}
