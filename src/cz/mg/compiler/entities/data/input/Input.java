package cz.mg.compiler.entities.data.input;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.entities.data.DataEntity;
import cz.mg.compiler.tasks.data.input.InputTask;


public abstract class Input extends DataEntity {
    public abstract InputTask createTask(ReadableText url);
}
