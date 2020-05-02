package cz.mg.language.entities.io.output;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.io.Bytes;
import cz.mg.language.tasks.data.output.FilesystemOutputTask;
import cz.mg.language.tasks.data.output.OutputTask;


public class FilesystemOutput extends Output {
    @Override
    public OutputTask createTask(ReadableText url, Bytes bytes) {
        return new FilesystemOutputTask(url, bytes);
    }
}
