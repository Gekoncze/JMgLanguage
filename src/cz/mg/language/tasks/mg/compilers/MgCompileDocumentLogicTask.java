package cz.mg.language.tasks.mg.compilers;

import cz.mg.collections.list.List;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.file.Document;
import cz.mg.language.entities.mg.logical.MgLogicalEntity;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.tasks.input.file.MgLoadTextFileTask;
import cz.mg.language.tasks.mg.builders.block.MgBuildRootBlockTask;
import cz.mg.language.tasks.mg.parsers.common.MgParsePageTask;
import cz.mg.language.tasks.mg.parsers.structured.MgParseBlocksTask;


public class MgCompileDocumentLogicTask extends MgCompileFileLogicTask {
    @Input
    private final Document document;

    @Output
    private MgLogicalComponent component;

    @Subtask
    private MgLoadTextFileTask loadTextFileTask;

    @Subtask
    private MgParsePageTask parsePageTask;

    @Input
    private MgParseBlocksTask parseBlocksTask;

    @Subtask
    private MgBuildRootBlockTask buildRootTask;

    public MgCompileDocumentLogicTask(Document document) {
        this.document = document;
    }

    public MgLogicalComponent getComponent() {
        return component;
    }

    @Override
    protected void onRun() {
        loadTextFileTask = new MgLoadTextFileTask(document.getFile());
        loadTextFileTask.run();

        parsePageTask = new MgParsePageTask(loadTextFileTask.getContent());
        parsePageTask.run();

        parseBlocksTask = new MgParseBlocksTask(parsePageTask.getPage());
        parseBlocksTask.run();

        buildRootTask = new MgBuildRootBlockTask(parseBlocksTask.getRoot());
        buildRootTask.run();

        component = buildRootTask.getComponent();
    }
}
