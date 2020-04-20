package cz.mg.compiler.entities.data.output;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.entities.data.Bytes;
import cz.mg.compiler.tasks.data.output.FilesystemOutputTask;
import cz.mg.compiler.tasks.data.output.OutputTask;


public class FilesystemOutput extends Output {
    @Override
    public OutputTask createTask(ReadableText url, Bytes bytes) {
        return new FilesystemOutputTask(url, bytes);
    }
}
