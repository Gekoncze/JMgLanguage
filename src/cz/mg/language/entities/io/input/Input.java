package cz.mg.language.entities.io.input;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.io.IOEntity;
import cz.mg.language.tasks.data.input.InputTask;


public abstract class Input extends IOEntity {
    public abstract InputTask createTask(ReadableText url);
}
