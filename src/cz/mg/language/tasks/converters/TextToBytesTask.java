package cz.mg.language.tasks.converters;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.io.Bytes;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public class TextToBytesTask extends ConverterTask {
    @Input
    private final ReadableText text;

    @Input
    private final Charset encoding;

    @Output
    private Bytes bytes = null;

    public TextToBytesTask(ReadableText text, Charset encoding) {
        this.text = text;
        this.encoding = encoding;
    }

    public Bytes getBytes() {
        return bytes;
    }

    @Override
    protected void onRun() {
        byte[] javaBytes = text.toString().getBytes(encoding);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(javaBytes.length);
        byteBuffer.put(byteBuffer);
        bytes = new Bytes(byteBuffer);
    }
}
