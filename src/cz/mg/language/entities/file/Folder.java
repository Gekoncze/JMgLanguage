package cz.mg.language.entities.file;

import cz.mg.collections.list.List;
import cz.mg.annotations.storage.Part;


public class Folder extends File {
    @Part
    private final List<File> files = new List<>();

    public Folder(java.io.File file) {
        super(file);
    }

    public List<File> getFiles() {
        return files;
    }
}
