package cz.mg.language.tasks.builders.mg;

import cz.mg.collections.ReadableCollection;
import cz.mg.collections.list.List;
import cz.mg.language.LanguageException;
import cz.mg.language.annotations.task.Input;
import cz.mg.language.annotations.task.Subtask;
import cz.mg.language.entities.text.structured.Block;
import cz.mg.language.entities.text.structured.parts.Part;
import cz.mg.language.tasks.builders.mg.child.Children;
import cz.mg.language.tasks.builders.mg.pattern.Patterns;


public abstract class MgBuildBlockTask extends MgBuildTask {
    @Input
    private final Block block;

    @Subtask
    private final List<MgBuildBlockTask> subtasks = new List();

    public MgBuildBlockTask(Block block) {
        this.block = block;
    }

    @Override
    protected final void onRun() {
        todo;
    }

    //    @Override
//    protected void onRun() {
//        match(PATTERNS, block.getParts());
//
//        usage = new MgUsageL();
//        buildPathTask = new MgBuildPathTask(block.getParts().get(1));
//        buildPathTask.onRun();
//        usage.getPath().addCollectionLast(buildPathTask.getPath());
//
//        for(Block childBlock : block.getBlocks()){
//            todo;
//        }
//
//        if(block.getBlocks().count() <= 0) throw new LanguageException("Missing as "); todo;
//    }

    private void match(Patterns patterns, ReadableCollection<Part> parts){
        if(!patterns.match(parts)){
            throw new LanguageException("Unrecognized pattern.");
        }
    }

    public abstract Patterns getPatterns();
    public abstract Children getChildren();
}
