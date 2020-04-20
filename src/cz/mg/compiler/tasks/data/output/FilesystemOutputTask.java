package cz.mg.compiler.tasks.data.output;

import cz.mg.collections.text.ReadableText;
import cz.mg.compiler.CompileException;
import cz.mg.compiler.annotations.task.Input;
import cz.mg.compiler.entities.data.Bytes;
import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;


public class FilesystemOutputTask extends OutputTask {
    @Input
    private final ReadableText url;

    @Input
    private final Bytes bytes;

    public FilesystemOutputTask(ReadableText url, Bytes bytes) {
        this.url = url;
        this.bytes = bytes;
    }

    @Override
    protected void onRun() {
        try(FileChannel output = FileChannel.open(new File(url.toString()).toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
            output.write(bytes.getByteBuffer());
        } catch (Exception e){
            throw new CompileException("Could not save file '" + url + "'.", e);
        }
    }
}
