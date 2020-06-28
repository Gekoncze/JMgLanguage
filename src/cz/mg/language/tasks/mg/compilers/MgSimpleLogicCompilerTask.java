package cz.mg.language.tasks.mg.compilers;

import cz.mg.collections.text.ReadableText;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.mg.logical.architecture.MgLogicalLocation;
import cz.mg.language.tasks.input.file.MgLoadFolderTreeTask;


public class MgSimpleLogicCompilerTask extends MgCompilerTask {
    @Input
    private final ReadableText sourceRootPath;

    @Output
    private MgLogicalLocation root;

    @Subtask
    private MgLoadFolderTreeTask loadFolderTreeTask;

    @Subtask
    private MgCompileFolderLogicTask compileFolderTask;

    public MgSimpleLogicCompilerTask(ReadableText sourceRootPath) {
        this.sourceRootPath = sourceRootPath;
    }

    public MgLogicalLocation getRoot() {
        return root;
    }

    @Override
    protected void onRun() {
        loadFolderTreeTask = new MgLoadFolderTreeTask(sourceRootPath);
        loadFolderTreeTask.run();

        compileFolderTask = new MgCompileFolderLogicTask(loadFolderTreeTask.getFolder());
        compileFolderTask.run();

        root = compileFolderTask.getLocation();
    }
}
