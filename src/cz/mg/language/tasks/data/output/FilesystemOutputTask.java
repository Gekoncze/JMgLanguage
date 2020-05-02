package cz.mg.language.tasks.data.output;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.entities.io.Bytes;
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
            throw new LanguageException("Could not save file '" + url + "'.", e);
        }
    }
}
