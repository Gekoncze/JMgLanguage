package cz.mg.compiler.entities.data.output;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.entities.data.Bytes;
import cz.mg.compiler.entities.data.DataEntity;
import cz.mg.compiler.tasks.data.output.OutputTask;


public abstract class Output extends DataEntity {
    public abstract OutputTask createTask(ReadableText url, Bytes bytes);
}
