package cz.mg.language.tasks.input.file;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.collections.text.Text;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MgLoadTextFileTask extends MgFileInputTask {
    @Input
    private final ReadableText path;

    @Input
    private final File file;

    @Output
    private ReadableText content;

    public MgLoadTextFileTask(ReadableText path) {
        this.path = path;
        this.file = new File(path.toString());
    }

    public MgLoadTextFileTask(File file) {
        this.path = null;
        this.file = file;
    }

    public ReadableText getContent() {
        return content;
    }

    @Override
    protected void onRun() {
        Text editableContent = new Text();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null){
                editableContent.append(line);
                editableContent.append("\n");
            }
        } catch (IOException e){
            throw new LanguageException("Could not load text file '" + path + "': " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        content = new ReadonlyText(editableContent);
    }
}
