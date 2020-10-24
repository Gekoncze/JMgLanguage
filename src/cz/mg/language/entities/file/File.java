package cz.mg.language.entities.file;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.Named;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.language.entities.Entity;


public abstract class File implements Named, Entity {
    @Part
    private final java.io.File file;

    @Value
    private final ReadableText name;

    public File(java.io.File file) {
        this.file = file;
        this.name = new ReadonlyText(file.getName());
    }

    public java.io.File getFile() {
        return file;
    }

    @Override
    public ReadableText getName() {
        return name;
    }
}
