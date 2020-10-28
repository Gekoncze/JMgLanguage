package cz.mg.language.tasks.mg.compiler;

import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Output;
import cz.mg.language.entities.file.Document;
import cz.mg.language.entities.mg.logical.components.MgLogicalComponent;
import cz.mg.language.tasks.input.file.MgLoadTextFileTask;
import cz.mg.language.tasks.mg.builder.block.MgBuildRootTask;
import cz.mg.language.tasks.mg.parser.MgParsePageTask;
import cz.mg.language.tasks.mg.composer.MgComposeBlocksTask;


public class MgCompileDocumentLogicTask extends MgCompileFileLogicTask {
    @Input
    private final Document document;

    @Output
    private MgLogicalComponent component;

    public MgCompileDocumentLogicTask(Document document) {
        this.document = document;
    }

    public MgLogicalComponent getComponent() {
        return component;
    }

    @Override
    protected void onRun() {
        MgLoadTextFileTask loadTextFileTask = new MgLoadTextFileTask(document.getFile());
        loadTextFileTask.run();

        MgParsePageTask parsePageTask = new MgParsePageTask(loadTextFileTask.getContent());
        parsePageTask.run();

        MgComposeBlocksTask parseBlocksTask = new MgComposeBlocksTask(parsePageTask.getPage());
        parseBlocksTask.run();

        MgBuildRootTask buildRootTask = new MgBuildRootTask(parseBlocksTask.getRoot());
        buildRootTask.run();

        component = buildRootTask.getComponent();
    }
}
