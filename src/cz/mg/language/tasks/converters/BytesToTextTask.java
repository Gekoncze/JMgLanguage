package cz.mg.language.tasks.converters;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.io.Bytes;
import java.nio.charset.Charset;


public class BytesToTextTask extends ConverterTask {
    @Input
    private final Bytes bytes;

    @Input
    private final Charset encoding;

    @Output
    private ReadableText text = null;

    public BytesToTextTask(Bytes bytes, Charset encoding) {
        this.bytes = bytes;
        this.encoding = encoding;
    }

    @Override
    protected void onRun() {
        text = new ReadonlyText(new String(bytes.getByteBuffer().array(), encoding));
    }
}
