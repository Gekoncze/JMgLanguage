package cz.mg.compiler.tasks.data.input;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.CompileException;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.annotations.task.Output;
import cz.mg.compiler.entities.data.Bytes;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;


public class FilesystemInputTask extends InputTask {
    @Input
    private final ReadableText url;

    @Output
    private Bytes bytes = null;

    public FilesystemInputTask(ReadableText url) {
        this.url = url;
    }

    public Bytes getBytes() {
        return bytes;
    }

    @Override
    protected void onRun() {
        File file = new File(url.toString());
        try(FileChannel input = FileChannel.open(file.toPath(), StandardOpenOption.READ)){
            if(file.length() >= Integer.MAX_VALUE) throw new RuntimeException("File is too large: " + file.length() + " out of " + Integer.MAX_VALUE + ".");
            bytes = new Bytes(ByteBuffer.allocateDirect((int) file.length()));
            input.read(bytes.getByteBuffer());
        } catch (Exception e){
            throw new CompileException("Could not load file '" + url + "'." , e);
        }
    }
}
