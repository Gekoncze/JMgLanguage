package cz.mg.language.entities.io.input;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.tasks.data.input.InputTask;
import cz.mg.language.tasks.data.input.FilesystemInputTask;


public class FilesystemInput extends Input {
    @Override
    public InputTask createTask(ReadableText url) {
        return new FilesystemInputTask(url);
    }
}
