package cz.mg.language.entities.io.output;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.entities.io.Bytes;
import cz.mg.language.entities.io.IOEntity;
import cz.mg.language.tasks.data.output.OutputTask;


public abstract class Output extends IOEntity {
    public abstract OutputTask createTask(ReadableText url, Bytes bytes);
}
